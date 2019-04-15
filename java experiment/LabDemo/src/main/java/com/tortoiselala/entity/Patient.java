package com.tortoiselala.entity;

import java.util.Date;

/**
 * * - T_BRXX(病人信息表)PatientInformationTableUnit
 * *      - BRBH(病人编号)PatientID
 * *      - BRMC(病人名称)PatientName
 * *      - DLKL(登录口令)PatientPassword
 * *      - YCJE(病人预存金额)PatientRechargeBalance
 * *      - DLRQ(最后一次登录日期以及时间)PatientLastLoginDate
 */
public class Patient {

    private String ID;
    private String name;
    private String password;
    private double chargeBalance;
    private Date lastLoginDate;

    public Patient() {
    }

    public Patient(String ID, String name, String password, double chargeBalance, Date lastLoginDate) {
        this.ID = ID;
        this.name = name;
        this.password = password;
        this.chargeBalance = chargeBalance;
        this.lastLoginDate = lastLoginDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getChargeBalance() {
        return chargeBalance;
    }

    public void setChargeBalance(double chargeBalance) {
        this.chargeBalance = chargeBalance;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", chargeBalance=" + chargeBalance +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Double.compare(patient.chargeBalance, chargeBalance) == 0 &&
                ID.equals(patient.ID) &&
                name.equals(patient.name) &&
                password.equals(patient.password) &&
                lastLoginDate.equals(patient.lastLoginDate);
    }
}
