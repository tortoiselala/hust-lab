package com.tortoiselala.mapper;

import com.tortoiselala.entity.RegInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RegInfoMapper {

    String getNameByID(String ID);

    String getPinyinByID(String ID);

    String getDepartmentIDByID(String ID);

    boolean isExpert(String ID);

    int getMaxNumberByID(String ID);

    double getFeeByID(String ID);

    List<RegInfo> getAll();

    void updateRegistrationNum(int vewValue, String id);
}
