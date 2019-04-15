package com.tortoiselala.service.impl;

import com.tortoiselala.dao.impl.PatientDao;
import com.tortoiselala.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientService {
    @Autowired
    PatientDao patientDao;

    public double getCharge(User user){
        return patientDao.getCharge(user);
    }
}
