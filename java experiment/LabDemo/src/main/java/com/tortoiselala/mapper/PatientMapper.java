package com.tortoiselala.mapper;

import com.tortoiselala.entity.Patient;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PatientMapper {
    String getNameByPatientID(String patientID);

    String getPasswordByPatientID(String patientID);

    double getChargeBalance(String patientID);

    Date getLastLoginDate(String patientID);

    Patient getByPatientID(String patientID);

    List<Patient> getAll();

    void updateLastLoginDate(Map<String, String> map);

    void updateChargeFee(Map<String, String> map);
}
