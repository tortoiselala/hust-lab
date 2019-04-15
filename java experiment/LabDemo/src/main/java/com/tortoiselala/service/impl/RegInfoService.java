package com.tortoiselala.service.impl;

import com.tortoiselala.dao.impl.RegInfoDao;
import com.tortoiselala.entity.RegInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RegInfoService {

    @Autowired
    RegInfoDao regInfoDao;
    public List<RegInfo> getAll(){
        return regInfoDao.getALl();
    }


}
