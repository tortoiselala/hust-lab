package com.tortoiselala.service.impl;

import com.tortoiselala.dao.impl.UserDao;
import com.tortoiselala.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserService   {

    @Autowired
    UserDao userDao;

    public boolean authCheck(User user){
        return this.userDao.authCheck(user);
    }


    public void updateLastLoginDate(User user) {
        this.updateLastLoginDate(user, new Date());
    }

    public void updateLastLoginDate(User user, Date date) {
        this.userDao.updateLastLoginDate(user, date);
    }
}
