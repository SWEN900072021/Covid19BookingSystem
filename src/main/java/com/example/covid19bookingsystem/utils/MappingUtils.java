package com.example.covid19bookingsystem.utils;

import com.example.covid19bookingsystem.domain.Timeslot;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

public class MappingUtils {

    public Timeslot requestToTimeslot(HttpServletRequest request) {
        Timeslot timeslot = new Timeslot();
        timeslot.setHealthcareProvider(parseInt(request.getParameter("healthcareProvider")));
        String dateTime = request.getParameter("date") + " " + request.getParameter("time") + ":00";
        timeslot.setDateTime(valueOf(dateTime));
        String duration = request.getParameter("duration");
        if (duration.isBlank()) {
            duration = "15";
        }
        timeslot.setDuration(parseInt(duration));
        timeslot.setLocation(request.getParameter("location"));
        return timeslot;
    }
}
