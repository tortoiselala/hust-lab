package com.tortoiselala.dao.impl;

import com.tortoiselala.dao.IUserDao;
import com.tortoiselala.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserDao implements IUserDao {
    @Autowired
    PatientDao patientDao;

    @Autowired
    DoctorDao doctorDao;

//    @Override
    public boolean authCheck(User user) {
        if (user.isDoctor()) {
            return doctorDao.checkAuthorityOfDoctor(user);
        } else {
            return patientDao.checkAuthorityOfPatient(user);
        }
    }

//    @Override
    public void updateLastLoginDate(User user, Date date) {
        if (user.isDoctor()) {
            doctorDao.updateDoctorLastLoginDate(user, date);
        } else {
            patientDao.updatePatientLastLoginDate(user, date);
        }
    }
}
