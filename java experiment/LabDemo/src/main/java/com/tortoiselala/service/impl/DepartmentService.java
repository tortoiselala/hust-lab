package com.tortoiselala.service.impl;

import com.tortoiselala.dao.impl.DepartmentDao;
import com.tortoiselala.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;

    public List<Department> getALl(){
        return departmentDao.getAll();
    }
}
