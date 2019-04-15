package com.tortoiselala.entity;

import java.util.Date;


/**
 * - T_GHXX(挂号信息表)RegistrationInformationTableUnit
 *      - GHBH(挂号的顺序编号，数字)QueueID
 *      - HZBH(号种编号)QueueTypeID
 *      - YSBH(医生编号)DoctorID
 *      - BRBH(病人编号)PatientID
 *      - GHRC(该病人该号种的挂号人次)NumberOfRegisteredPerson
 *      - THBZ(退号标志=true为已退号码)CancelingFlag
 *      - GHFY(病人的实际挂号费用)QueueFee
 *      - RQSJ(挂号日期时间)QueueDate
 */
public class Registration {
    private String ID;
    private String registrationID;
    private String doctorID;
    private String patientID;
    private int totalNumberOfRegistered;
    private boolean cancel;
    private double fee;
    private Date date;

    public Registration() {
    }

    public Registration(String ID, String registrationID, String doctorID, String patientID, int totalNumberOfRegistered, boolean cancel, double fee, Date date) {
        this.ID = ID;
        this.registrationID = registrationID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.totalNumberOfRegistered = totalNumberOfRegistered;
        this.cancel = cancel;
        this.fee = fee;
        this.date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public int getTotalNumberOfRegistered() {
        return totalNumberOfRegistered;
    }

    public void setTotalNumberOfRegistered(int totalNumberOfRegistered) {
        this.totalNumberOfRegistered = totalNumberOfRegistered;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "ID='" + ID + '\'' +
                ", registrationID='" + registrationID + '\'' +
                ", doctorID='" + doctorID + '\'' +
                ", patientID='" + patientID + '\'' +
                ", totalNumberOfRegistered=" + totalNumberOfRegistered +
                ", cancel=" + cancel +
                ", fee=" + fee +
                ", date=" + date +
                '}';
    }
}
