package com.tortoiselala.service.impl;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.dao.impl.DoctorDao;
import com.tortoiselala.dao.impl.PatientDao;
import com.tortoiselala.dao.impl.RegInfoDao;
import com.tortoiselala.dao.impl.RegistrationDao;
import com.tortoiselala.entity.Doctor;
import com.tortoiselala.entity.RegInfo;
import com.tortoiselala.entity.Registration;
import com.tortoiselala.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;

public class RegistrationService {

    private static final Object[] LOCK = new Object[0];

    @Autowired
    RegInfoDao regInfoDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    PatientDao patientDao;

    @Autowired
    RegistrationDao registrationDao;

    public void registration(Map<String, String> parameterMap, User user) throws Exception {
        String doctorID = parameterMap.get(CommonConstant.DOCTOR_NAME);
        // 校验医生信息
        Doctor doctor = doctorDao.getDoctorByDoctorID(doctorID);
        if (doctor == null) {
            throw new Exception("医生ID不正确");
        }
        // 校验医生所属科室信息
        String departmentID = parameterMap.get(CommonConstant.DEPARTMENT_NAME);
        if (!departmentID.equals(doctor.getDepartmentID())) {
            throw new Exception("医生所属科室不正确");

        }

        // 校验号种编号
        String queueID = parameterMap.get(CommonConstant.QUEYE_NAME);
        String name = regInfoDao.getNameByID(queueID);
        if (name == null) {
            throw new Exception("号种编号不正确");
        }

        // 获取用户余额
        double chargeFee = patientDao.getCharge(user);

        // 获得号种所需费用
        double fee = regInfoDao.getFeeByID(queueID);

        // 获取用户提交的金额
        double userInputCharge = Double.valueOf(
                parameterMap.get(CommonConstant.INPUT_CHARGE) == null
                        ?
                        "0"
                        :
                        parameterMap.get(CommonConstant.INPUT_CHARGE) );


        // 校验用户余额
        if (chargeFee < fee && (chargeFee + userInputCharge) < fee) {
            throw new Exception("费用不够");
        }
        BigInteger currentRegistrationNum = null;
        double num = 0;
        // 获取号码
        synchronized (LOCK) {
            // 获取的挂号编号
            currentRegistrationNum = new BigInteger(registrationDao.getNextNum()).add(new BigInteger("1"));
            // 获得的当前号种已经挂号人数
            int currentRegistrationCount = regInfoDao.getNum(queueID) + 1;
            String regID = queueID;
            String patientID = user.getUserId();
            Registration newItem = new Registration();
            newItem.setID(currentRegistrationNum.toString());
            newItem.setRegistrationID(queueID);
            newItem.setDoctorID(doctorID);
            newItem.setPatientID(patientID);
            newItem.setTotalNumberOfRegistered(currentRegistrationCount);
            newItem.setCancel(false);
            newItem.setFee(fee);
            newItem.setDate(new Date());
            // 扣费
            if(chargeFee >= fee){
                patientDao.updateFee(chargeFee - fee, patientID);
            }else{
                num = chargeFee + userInputCharge - fee;
                patientDao.updateFee(0, patientID);
            }
            // 刷新号种信息表的挂号人数
            regInfoDao.updateRegistrationNum(queueID, currentRegistrationCount);
            // 插入挂号信息
            registrationDao.insert(newItem);
            throw new Exception("挂号成功, 号码为 " + currentRegistrationNum.toString() + "\t实际挂号费用" + fee + "\t 找零为 " + num);
        }
    }
}
