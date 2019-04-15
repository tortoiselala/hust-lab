package com.tortoiselala.entity;

import java.util.Date;

/**
 * - T_KSYS(科室医生表)DepartmentDoctorTableUnit
 * - YSBH(医生编号)DoctorID
 * - KSBH(科室编号)DepartmentID
 * - YSMC(医生名称)DoctorName
 * - PYZS(医生名称的拼音字首)DoctorPinyinPrefix
 * - DLKL(登录口令)DoctorPassword
 * - SFZJ(是否专家)DoctorType
 * - DLRQ(最后一次登录日期及时间)DoctorLastLoginDate
 */
public class Doctor {

    private String doctorID;
    private String departmentID;
    private String doctorName;
    private String doctorPinyinPrefix;
    private String doctorPassword;
    private boolean expert;
    private Date doctorLastLoginDate;

    public Doctor() {
    }

    public Doctor(String doctorID, String departmentID, String doctorName, String doctorPinyinPrefix, String doctorPassword, boolean expert, Date doctorLastLoginDate) {
        this.doctorID = doctorID;
        this.departmentID = departmentID;
        this.doctorName = doctorName;
        this.doctorPinyinPrefix = doctorPinyinPrefix;
        this.doctorPassword = doctorPassword;
        this.expert = expert;
        this.doctorLastLoginDate = doctorLastLoginDate;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPinyinPrefix() {
        return doctorPinyinPrefix;
    }

    public void setDoctorPinyinPrefix(String doctorPinyinPrefix) {
        this.doctorPinyinPrefix = doctorPinyinPrefix;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public Date getDoctorLastLoginDate() {
        return doctorLastLoginDate;
    }

    public void setDoctorLastLoginDate(Date doctorLastLoginDate) {
        this.doctorLastLoginDate = doctorLastLoginDate;
    }

    @Override
    public String toString() {
        return "LabDoctorTableUnit{" +
                "doctorID='" + doctorID + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPinyinPrefix='" + doctorPinyinPrefix + '\'' +
                ", doctorPassword='" + doctorPassword + '\'' +
                ", expert=" + expert +
                ", doctorLastLoginDate=" + doctorLastLoginDate +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Doctor another = null;
        if (obj == null) {
            return false;
        }
        another = (Doctor) obj;
        if (
                this.doctorID.equals(another.doctorID)
                        && this.departmentID.equals(another.departmentID)
                        && this.doctorName.equals(another.doctorName)
                        && this.doctorPinyinPrefix.equals(another.doctorPinyinPrefix)
                        && this.doctorPassword.equals(another.doctorPassword)
                        && this.expert == another.expert
                        && this.doctorLastLoginDate.equals(another.doctorLastLoginDate)
        ) {
            return true;
        }
        return false;
    }
}
