package com.tortoiselala.mapper;

import com.tortoiselala.entity.Registration;

import java.util.Date;
import java.util.List;

public interface RegistrationMapper {
    String getRegisterNumberByID(String ID);

    String getDoctorIDByID(String ID);

    String getPatientIDByID(String ID);

    int getNumberOfRegistered(String ID);

    boolean getStatus(String ID);

    double getFee(String ID);

    Date getDate(String ID);

    Registration getByID(String ID);

    List<Registration> getAll();

    String getNextNum();

    void insertNewItem(Registration registration);
}
