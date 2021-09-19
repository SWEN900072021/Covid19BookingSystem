package com.example.covid19bookingsystem.controller.vr;

import com.example.covid19bookingsystem.domain.HealthCareProvider;
import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineType;
import com.example.covid19bookingsystem.mapper.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchTypeController", value = "/vr/searchType")
public class SearchTypeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // VaccineTypes display for dropdown
        ArrayList<VaccineType> allVaccineTypes = VaccineTypeMapper.getAllVaccineTypes();
        request.getSession().setAttribute("allVaccineTypes", allVaccineTypes);
        request.getRequestDispatcher("vr/searchType.jsp").forward(request, response);

        // Question display
        if(request.getParameter("vaccineType") != null){
            List<Question> questionsForVaccineType
                    = VaccineQuestionMapper.getQuestionIdsForVaccineType(request.getParameter("vaccineType"));

            List<Question> questionsToDisplay = new ArrayList<>();

            for (Question question : questionsForVaccineType){
                Question questionToDisplay = QuestionMapper.getQuestionById(question);
                questionsToDisplay.add(questionToDisplay);
            }
            request.getSession().setAttribute("questionNumber", questionsToDisplay.size());
            request.getSession().setAttribute("questionsToDisplay", questionsToDisplay);
        }

        // Result

        Boolean result = processSearchTypeRequest(request, response);
        if (!result) {
            request.setAttribute("failure", "true");
            request.getRequestDispatcher("searchType.jsp").forward(request, response);
        }
    }

    private Boolean processSearchTypeRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HealthCareProvider> HCPs;
        if (request.getParameter("searchBy").equals("area")) {
            HCPs = HealthCareProviderMapper.findHCPByPostCode(request.getParameter("queryField"));
        } else {
            HCPs = HealthCareProviderMapper.findHCPByName(request.getParameter("queryField"));
        }
        return findTimeslotsHelper(HCPs, request, response);
    }

    private Boolean findTimeslotsHelper(List<HealthCareProvider> HCPs, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!HCPs.isEmpty()) {
            List<Timeslot> timeslots = new ArrayList<>();
            for (HealthCareProvider HCP : HCPs) {
                List<Timeslot> timeslotsForHCP = TimeslotMapper.findTimeslotByHcpAndVaccineType(HCP, request.getParameter("vaccineType"));
                if (!timeslotsForHCP.isEmpty()) {
                    timeslots.addAll(timeslotsForHCP);
                }
            }
            if (!timeslots.isEmpty()) {
                request.getSession().setAttribute("allAvailableTimeslotDates", timeslots);
                request.getSession().setAttribute("hcpList", HCPs);
                request.getRequestDispatcher("/bookDate").forward(request, response);
                return true;
            }
        }
        return false;
    }
    private Boolean sendVaccineTypesList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<VaccineType> allVaccineTypes = VaccineTypeMapper.getAllVaccineTypes();
        if (!allVaccineTypes.isEmpty()) {
            request.getSession().setAttribute("allVaccineTypes", allVaccineTypes);
            request.getRequestDispatcher("/searchType.jsp").forward(request, response);
            System.out.println(allVaccineTypes);
            return true;
        }
        return false;
    }

}
