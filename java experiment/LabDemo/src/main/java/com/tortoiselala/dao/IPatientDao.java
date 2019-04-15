package com.tortoiselala.dao;

import com.tortoiselala.entity.User;

import java.util.Date;

public interface IPatientDao {

    void updatePatientLastLoginDate(User user, Date date);
}
