package com.tortoiselala.mapper;


import com.tortoiselala.entity.Registration;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class ResistrationMapperTest {
    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void TestGetRegisterNumberByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            String departmentName = resistrationMapper.getRegisterNumberByID(ID);
            Assert.assertTrue("TestGetRegisterNumberByID", departmentName.equals("000001"));
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
    public void TestGetDoctorIDByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            String doctorID = resistrationMapper.getDoctorIDByID(ID);
            Assert.assertTrue("TestGetDoctorIDByID", doctorID.equals("000001"));
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
    public void TestGetPatientIDByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            String patientID = resistrationMapper.getPatientIDByID(ID);
            Assert.assertTrue("TestGetPatientIDByID", patientID.equals("000001"));
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
    public void TestGetNumberOfRegistered(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            int num = resistrationMapper.getNumberOfRegistered(ID);
            Assert.assertTrue("TestGetNumberOfRegistered", num == 1);
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
    public void TestGetStatus(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            boolean status = resistrationMapper.getStatus(ID);
            Assert.assertTrue("TestGetNumberOfRegistered", !status);
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
    public void TestGetFee(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            double fee = resistrationMapper.getFee(ID);
            System.out.println(fee);
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
    public void TestGetDate(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            Date date = resistrationMapper.getDate(ID);
            System.out.println(date);
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
    public void TestGetByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            Registration registration = resistrationMapper.getByID(ID);
            System.out.println(registration);
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
    public void TestGetAll(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper resistrationMapper = session.getMapper(RegistrationMapper.class);
            List<Registration> list = resistrationMapper.getAll();
            for(Registration e : list){
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
    public void TestGetNextNum(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper registrationMapper = session.getMapper(RegistrationMapper.class);
            String num = registrationMapper.getNextNum();
            System.out.println(num);
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
    public void TestInsertNewItem(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegistrationMapper registrationMapper = session.getMapper(RegistrationMapper.class);
            Registration newItem = new Registration();
            newItem.setID("000018");
            newItem.setRegistrationID("000001");
            newItem.setDoctorID("000001");
            newItem.setPatientID("000001");
            newItem.setTotalNumberOfRegistered(20);
            newItem.setCancel(false);
            newItem.setFee(20);
            newItem.setDate(new Date());
            registrationMapper.insertNewItem(newItem);
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