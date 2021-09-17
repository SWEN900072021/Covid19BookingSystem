package com.example.covid19bookingsystem.utils;
import com.example.covid19bookingsystem.datasource.DBConnection;
import com.example.covid19bookingsystem.domain.Question;
import com.example.covid19bookingsystem.domain.Timeslot;
import com.example.covid19bookingsystem.domain.VaccineType;
import com.example.covid19bookingsystem.mapper.QuestionMapper;
import com.example.covid19bookingsystem.mapper.TimeslotMapper;
import com.example.covid19bookingsystem.mapper.VaccineQuestionMapper;
import com.example.covid19bookingsystem.mapper.VaccineTypeMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.SQLException;
import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor

public class UnitOfWork {

    public VaccineType vaccineType;

    private ArrayList<Object> newObjList = new ArrayList<Object>();

    public void registerNew(Object o) {
        newObjList.add(o);
    }

    public void commit() throws SQLException {
        DBConnection.getDbConnection().setAutoCommit(false);

        for (Object o : newObjList){
            if (o instanceof Timeslot){
                TimeslotMapper.insert((Timeslot) o);
            }
            if (o instanceof VaccineType){
                VaccineTypeMapper.insert((VaccineType) o);
                vaccineType = (VaccineType) o;
            }
            if (o instanceof Question){
                QuestionMapper.insert((Question) o);
                VaccineQuestionMapper.insert(vaccineType, (Question) o);
            }

        }

        // Commit all queries
        DBConnection.getDbConnection().commit();
        DBConnection.close(null,null);
        DBConnection.getDbConnection().setAutoCommit(true);

        // Reset UoW
        newObjList.clear();
    }
}
