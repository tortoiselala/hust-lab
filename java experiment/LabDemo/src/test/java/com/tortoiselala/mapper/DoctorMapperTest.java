package com.tortoiselala.mapper;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.Doctor;
import com.tortoiselala.entity.PatientUnitForShow;
import com.tortoiselala.utils.MybatisUtil;
import com.tortoiselala.utils.Tools;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DoctorMapperTest {
    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void TestGetDepartmentIDByDoctorID() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            String departmentID = doctorMapper.getDepartmentIDByDoctorID(doctorID);
            Assert.assertTrue("TestGetDepartmentIDByDoctorID", departmentID.equals("000001"));
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
    public void TestGetNameByDoctorID() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            String doctorName = doctorMapper.getNameByDoctorID(doctorID);
            Assert.assertTrue("TestGetNameByDoctorID", doctorName.equals("赵一"));
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
    public void TestGetPasswordByDoctorID() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            String password = doctorMapper.getPasswordByDoctorID(doctorID);
            Assert.assertTrue("TestGetPasswordByDoctorID", password.equals("ZY"));
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
    public void TestGetTypeByDoctorID() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            boolean type = doctorMapper.getTypeByDoctorID(doctorID);
            Assert.assertTrue("TestGetTypeByDoctorID", type);
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
    public void TestGetDoctorByDoctorID() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            Doctor doctor = doctorMapper.getDoctorByDoctorID(doctorID);
            System.out.println(doctor);
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
    public void TestGetDoctorList() {
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            List<Doctor> list = doctorMapper.getDoctorList();
            for (Doctor e : list) {
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
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.LAST_VISITED, Tools.getFormatedDate());
            map.put(CommonConstant.USER_ID, doctorID);
            doctorMapper.updateLastLoginDate(map);
            Doctor doctor = doctorMapper.getDoctorByDoctorID(doctorID);
            System.out.println(doctor);
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
    public void TestGetRegistrationInformation(){
        SqlSession session = null;
        String doctorID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);

            List<PatientUnitForShow> list = doctorMapper.getRegistrationInformation(doctorID);
            for(PatientUnitForShow e : list){
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
}