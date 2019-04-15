package com.tortoiselala.entity;


import java.util.Objects;

/**
 * - T_HZXX(号种信息表)NumberInformationTableUnit
 * - HZBH(号种编号)QueueTypeID
 * - HZMC(号种名称)QueueTypeName
 * - PYZS(号种名称的拼音字首)QueueTypePinyinPrefix
 * - KSBH(号种所属科室)QueueTypeDepartmentID
 * - SFZJ(是否专家号)QueueType
 * - GHRS(每日限定的挂号人数)MaxNumberOfPeople
 * - GHFY(挂号费)RegistrationFee
 */
public class RegInfo {

    private String ID;
    private String name;
    private String pinyinPrefix;
    private String departmentID;
    private boolean expert;
    private int maxNumberOfPeople;
    private double regFee;

    public RegInfo() {
    }

    public RegInfo(String ID, String name, String pinyinPrefix, String departmentID, boolean expert, int maxNumberOfPeople, double regFee) {
        this.ID = ID;
        this.name = name;
        this.pinyinPrefix = pinyinPrefix;
        this.departmentID = departmentID;
        this.expert = expert;
        this.maxNumberOfPeople = maxNumberOfPeople;
        this.regFee = regFee;
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

    public String getPinyinPrefix() {
        return pinyinPrefix;
    }

    public void setPinyinPrefix(String pinyinPrefix) {
        this.pinyinPrefix = pinyinPrefix;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public boolean isExpert() {
        return expert;
    }

    public void setExpert(boolean expert) {
        this.expert = expert;
    }

    public int getMaxNumberOfPeople() {
        return maxNumberOfPeople;
    }

    public void setMaxNumberOfPeople(int maxNumberOfPeople) {
        this.maxNumberOfPeople = maxNumberOfPeople;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    @Override
    public String toString() {
        return "RegInfo{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", pinyinPrefix='" + pinyinPrefix + '\'' +
                ", departmentID='" + departmentID + '\'' +
                ", expert=" + expert +
                ", maxNumberOfPeople=" + maxNumberOfPeople +
                ", regFee=" + regFee +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegInfo regInfo = (RegInfo) o;
        return expert == regInfo.expert &&
                maxNumberOfPeople == regInfo.maxNumberOfPeople &&
                Double.compare(regInfo.regFee, regFee) == 0 &&
                ID.equals(regInfo.ID) &&
                name.equals(regInfo.name) &&
                pinyinPrefix.equals(regInfo.pinyinPrefix) &&
                departmentID.equals(regInfo.departmentID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, pinyinPrefix, departmentID, expert, maxNumberOfPeople, regFee);
    }
}
