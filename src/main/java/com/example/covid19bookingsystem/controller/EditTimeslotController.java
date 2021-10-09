package com.example.covid19bookingsystem.controller;

import com.example.covid19bookingsystem.datasource.ExclusiveLockManager;
import com.example.covid19bookingsystem.domain.Address;
import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineType;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.mapper.VaccineTypeMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "editTimeslotController", value = "/editTimeslot")
public class EditTimeslotController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userDetails") != null) {
            request.removeAttribute("completedEditingTimeslot");
            request.removeAttribute("timeslotId");
            request.removeAttribute("allVaccineTypesToChoose");
            request.removeAttribute("chosenTimeslotDetails");
            HealthCareProvider hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
            List<Timeslot> editableTimeslots = TimeslotMapper.findUnbookedTimeslotsByOrganisationalId(hcp.getOrganisationalId());
            if (!editableTimeslots.isEmpty()) {
                request.getSession().setAttribute("editableTimeslots", editableTimeslots);

                String view = "/hcp/editTimeslot.jsp";
                request.getRequestDispatcher(view).forward(request, response);
            }
            else {
                request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("completedEditingTimeslot") != null) {
            confirmTimeslotDetails(request, response);
        } else {
            openTimeslotDetails(request, response);
        }
    }

    private void confirmTimeslotDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Timeslot timeslot = new Timeslot();
        String dateTime = request.getParameter("date") + " " + request.getParameter("time") + ":00";
        timeslot.setId(Integer.parseInt(request.getParameter("timeslotId")));
        timeslot.setDateTime(Timestamp.valueOf(dateTime));
        timeslot.setVaccineType(request.getParameter("vaccineType"));
        timeslot.setDuration(Integer.parseInt(request.getParameter("duration")));
        Address address = new Address();
        address.setAddressLine1(request.getParameter("addressLine1"));
        address.setAddressLine2(request.getParameter("addressLine2"));
        address.setState(request.getParameter("state"));
        address.setPostcode(request.getParameter("postcode"));
        address.setCountry(request.getParameter("country"));
        timeslot.setAddress(address);
        timeslot.setVersion(Integer.parseInt(request.getParameter("version")));
        TimeslotMapper.updateTimeslotDetails(timeslot);
        ExclusiveLockManager.getInstance().releaseLock(Integer.parseInt(request.getParameter("timeslotId")));
        doGet(request, response);
    }

    private void openTimeslotDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
        List<Timeslot> editableTimeslots = (List<Timeslot>) request.getSession().getAttribute("editableTimeslots");
        Timeslot chosenTimeslot = null;
        HashMap<String, String> timeslotDetails = new HashMap<String, String>();
        for (Timeslot timeslot : editableTimeslots) {
            if (timeslot.getId() == Integer.parseInt(request.getParameter("timeslotId"))) {
                timeslotDetails.put("id", timeslot.getId().toString());
                timeslotDetails.put("date", dateFormatter.format(timeslot.getDateTime().getTime()));
                timeslotDetails.put("time", timeFormatter.format(timeslot.getDateTime().getTime()).toUpperCase() );
                timeslotDetails.put("vaccineType", timeslot.getVaccineType());
                timeslotDetails.put("duration", timeslot.getDuration().toString());
                timeslotDetails.put("addressLine1", timeslot.getAddress().getAddressLine1());
                timeslotDetails.put("addressLine2", timeslot.getAddress().getAddressLine2());
                timeslotDetails.put("state", timeslot.getAddress().getState());
                timeslotDetails.put("postcode", timeslot.getAddress().getPostcode());
                timeslotDetails.put("country", timeslot.getAddress().getCountry());
                timeslotDetails.put("version", timeslot.getVersion().toString());
                timeslotDetails.put("hcpOrgId", timeslot.getHealthcareProvider().getOrganisationalId().toString());
                timeslotDetails.put("hcpName", timeslot.getHealthcareProvider().getHealthCareProviderName());
                timeslotDetails.put("hcpType", timeslot.getHealthcareProvider().getHealthCareProviderType().name());
                chosenTimeslot = timeslot;
            }
        }
        List<VaccineType> allVaccineTypes = VaccineTypeMapper.getAllVaccineTypes();
        if (chosenTimeslot != null && !allVaccineTypes.isEmpty() && request.getSession().getAttribute("userDetails") != null) {
            HealthCareProvider hcp = (HealthCareProvider) request.getSession().getAttribute("userDetails");
            Boolean outcome = ExclusiveLockManager.getInstance().acquireLock(Integer.parseInt(request.getParameter("timeslotId")), hcp.getId());
            if (outcome) {
                request.setAttribute("allVaccineTypesToChoose", allVaccineTypes);
                request.setAttribute("chosenTimeslotDetails", timeslotDetails);
                request.getRequestDispatcher("/hcp/editTimeslot.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/outcome.jsp?success=lock_error").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/outcome.jsp?success=false").forward(request, response);
        }
    }

}
