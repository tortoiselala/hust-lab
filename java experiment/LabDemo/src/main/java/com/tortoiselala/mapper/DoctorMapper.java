package com.tortoiselala.mapper;

import com.tortoiselala.entity.Doctor;
import com.tortoiselala.entity.PatientUnitForShow;
import com.tortoiselala.entity.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface DoctorMapper {
    public String getDepartmentIDByDoctorID(String doctorID);

    public String getNameByDoctorID(String doctorID);

    public String getPasswordByDoctorID(String doctorID);

    public boolean getTypeByDoctorID(String doctorID);

    public Doctor getDoctorByDoctorID(String doctorIDs);

    public List<Doctor> getDoctorList();

    public void updateLastLoginDate(Map<String, String> map);

    public List<PatientUnitForShow> getRegistrationInformation(String doctorID);
}
