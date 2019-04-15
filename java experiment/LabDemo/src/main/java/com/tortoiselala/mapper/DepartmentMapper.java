package com.tortoiselala.mapper;

import com.tortoiselala.entity.Department;

import java.util.List;

public interface DepartmentMapper {
    public String getDepartmentNameByDepartmentID(String departmentID);
    public String getDepartmentPinyinByDepartmentID(String departmentID);
    public Department getDepartmentByDepartmentID(String departmentID);
    public List<Department> getDepartmentList();
}
