package com.tortoiselala.entity;

/**
 * - T_KSXX(科室信息表)DepartmentInformationTableUnit
 *      - KSBH(科室编号)DepartmentID
 *      - KSMC(科室名称)DepartmentName
 *      - PYZS(科室名称的拼音字首)DepartmentPinyinPrefix
 */
public class Department {
    private String departmentID;
    private String departmentName;
    private String departmentPinyinPrefix;

    public Department() {
    }

    public Department(String departmentID, String departmentName, String departmentPinyinPrefix) {
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.departmentPinyinPrefix = departmentPinyinPrefix;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentPinyinPrefix() {
        return departmentPinyinPrefix;
    }

    public void setDepartmentPinyinPrefix(String departmentPinyinPrefix) {
        this.departmentPinyinPrefix = departmentPinyinPrefix;
    }

    @Override
    public String toString() {
        return "LabInfoTableUnit{" +
                "DepartmentID='" + departmentID + '\'' +
                ", DepartmentName='" + departmentName + '\'' +
                ", DepartmentPinyinPrefix='" + departmentPinyinPrefix + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Department another = null;
        if(obj == null) {
            return false;
        }else{
            another = (Department)obj;
        }
        if(
                this.departmentID.equals(another.departmentID)
                && this.departmentName.equals(another.departmentName)
                && this.departmentPinyinPrefix.equals(another.departmentPinyinPrefix)
        ){
            return true;
        }
        return false;
    }
}
