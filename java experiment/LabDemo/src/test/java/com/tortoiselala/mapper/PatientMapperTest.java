package com.tortoiselala.mapper;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.Doctor;
import com.tortoiselala.entity.Patient;
import com.tortoiselala.utils.MybatisUtil;
import com.tortoiselala.utils.Tools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientMapperTest {
    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void TestGetNameByPatientID() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            String patientName = doctorMapper.getNameByPatientID(patientID);
            Assert.assertTrue("TestGetNameByPatientID", patientName.equals("一"));
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

    @Test
    public void TestGetPasswordByPatientID() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            String patientName = doctorMapper.getPasswordByPatientID(patientID);
            Assert.assertTrue("TestGetPasswordByPatientID", patientName.equals("1"));
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

    @Test
    public void TestGetChargeBalance() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            double chargeBalance = doctorMapper.getChargeBalance(patientID);
            Assert.assertTrue("TestGetPasswordByPatientID", chargeBalance == 100);
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

    @Test
    public void TestGetLastLoginDate() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            Date lastLoginDate = doctorMapper.getLastLoginDate(patientID);
            System.out.println(lastLoginDate);
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

    @Test
    public void TestGetByPatientID() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            Patient patient = doctorMapper.getByPatientID(patientID);
            System.out.println(patient);
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

    @Test
    public void TestGetAll() {
        SqlSession session = null;
        String patientID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper doctorMapper = session.getMapper(PatientMapper.class);
            List<Patient> list = doctorMapper.getAll();
            for(Patient e : list){
                System.out.println(e);
            }
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

    @Test
    public void TestUpdateLastLoginDate(){
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.LAST_VISITED, Tools.getFormatedDate());
            map.put(CommonConstant.USER_ID, doctorID);
            patientMapper.updateLastLoginDate(map);
            Patient patient = patientMapper.getByPatientID(doctorID);
            System.out.println(patient);
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

    @Test
    public void TestUpdateChargeFee(){
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            PatientMapper patientMapper = session.getMapper(PatientMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.NEW_VALUE, "10");
            map.put(CommonConstant.USER_ID, doctorID);
            patientMapper.updateChargeFee(map);
            Patient patient = patientMapper.getByPatientID(doctorID);
            System.out.println(patient);
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
}