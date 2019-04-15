package com.tortoiselala.dao.impl;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.User;
import com.tortoiselala.mapper.PatientMapper;
import com.tortoiselala.utils.MybatisUtil;
import com.tortoiselala.utils.Tools;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PatientDao implements com.tortoiselala.dao.IPatientDao {

    public boolean checkAuthorityOfPatient(User user){
        String userId = user.getUserId();
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            String password = patientMapper.getPasswordByPatientID(userId);
            session.commit();
            if(password.equals(user.getPassword())){
                return true;
            }
            return false;
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }
    @Override
    public void updatePatientLastLoginDate(User user, Date date){
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.LAST_VISITED, Tools.getFormatedDate(date));
            map.put(CommonConstant.USER_ID, user.getUserId());
            patientMapper.updateLastLoginDate(map);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public double getCharge(User user){
        SqlSession session = null;
        double fee = 0;
        try {
            session = MybatisUtil.getSqlSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            fee = patientMapper.getChargeBalance(user.getUserId());
            session.commit();
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return fee;
    }

    public void updateFee(double newValue, String id){
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.NEW_VALUE, Double.toString(newValue));
            map.put(CommonConstant.USER_ID, id);
            patientMapper.updateChargeFee(map);
            session.commit();
        } catch (Exception e) {
            if(session != null){
                session.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
