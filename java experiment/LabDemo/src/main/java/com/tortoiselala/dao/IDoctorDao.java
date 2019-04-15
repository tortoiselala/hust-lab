package com.tortoiselala.dao;

import com.tortoiselala.entity.PatientUnitForShow;
import com.tortoiselala.entity.User;

import java.util.Date;
import java.util.List;

public interface IDoctorDao {
    boolean checkAuthorityOfDoctor(User user);

    void updateDoctorLastLoginDate(User user, Date date);

    List<PatientUnitForShow> getPatientListByDoctor(User user);

    boolean getDoctorType(User user);

    String getDoctorNameByID(String doctorID);

    String getDepartmentIDByID(String id);
}
