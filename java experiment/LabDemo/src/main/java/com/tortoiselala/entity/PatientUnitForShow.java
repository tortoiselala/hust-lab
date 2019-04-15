package com.tortoiselala.entity;

import java.util.Date;

public class PatientUnitForShow {
    private String registrationID;
    private String patientName;
    private Date date;
    private boolean doctor;

    public PatientUnitForShow() {
    }

    public PatientUnitForShow(String registrationID, String patientName, Date date) {
        this.registrationID = registrationID;
        this.patientName = patientName;
        this.date = date;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "PatientUnitForShow{" +
                "registrationID='" + registrationID + '\'' +
                ", patientName='" + patientName + '\'' +
                ", date=" + date +
                '}';
    }
}
