package com.example.covid19bookingsystem.utils;

import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor

public class UnitOfWork {
    private ArrayList<Object> newObjList = new ArrayList<Object>();

    public void registerNew(Object o) {
        newObjList.add(o);
    }

    public void commit() throws SQLException {
        DBConnection.getDbConnection().setAutoCommit(false);

        for (Object o : newObjList) {
            if (o instanceof Timeslot) {
                TimeslotMapper.insert((Timeslot) o);
            }
        }

        // Commit all queries
        DBConnection.getDbConnection().commit();
        DBConnection.close(null, null);
        DBConnection.getDbConnection().setAutoCommit(true);

        // Reset UoW
        newObjList.clear();
    }
}
