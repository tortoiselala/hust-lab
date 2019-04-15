package com.tortoiselala.dao;

import com.tortoiselala.entity.User;

import java.util.Date;

public interface IUserDao {
    boolean authCheck(User user);

    void updateLastLoginDate(User user, Date date);
}
