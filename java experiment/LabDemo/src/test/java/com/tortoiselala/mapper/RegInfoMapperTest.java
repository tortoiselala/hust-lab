package com.tortoiselala.mapper;

import com.tortoiselala.entity.RegInfo;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegInfoMapperTest {
    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void TestGetNameByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            String name = regInfoMapper.getNameByID(ID);
            Assert.assertTrue("TestGetNameByID", name.equals("高尿酸痛风专病门诊"));
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
    public void TestGetPinyinByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            String pinyin = regInfoMapper.getPinyinByID(ID);
            System.out.println(pinyin);
            Assert.assertTrue("TestGetPinyinByID", pinyin.equals("GNSZ"));
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
    public void TestGetDepartmentIDByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            String departmentID = regInfoMapper.getDepartmentIDByID(ID);
            Assert.assertTrue("TestGetDepartmentIDByID", departmentID.equals("000001"));
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
    public void TestIsExpert(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            boolean expert = regInfoMapper.isExpert(ID);
            Assert.assertTrue("TestIsExpert", expert);
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
    public void TestGetMaxNumberByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            int num = regInfoMapper.getMaxNumberByID(ID);
            System.out.println(num);
            Assert.assertTrue("TestGetMaxNumberByID", num == 0);
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
    public void TestGetFeeByID(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            double fee = regInfoMapper.getFeeByID(ID);
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
    public void TestGetAll(){
        SqlSession session = null;
        String ID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            List<RegInfo> list = regInfoMapper.getAll();
            session.commit();
            for (RegInfo e : list){
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
    public void TestUpdateRegistrationNum(){
        SqlSession session = null;
        String ID = "000001";
        int num = 40;
        try {
            session = sqlSessionFactory.openSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            Map<String, String> map = new HashMap<>();
            regInfoMapper.updateRegistrationNum(num, ID);
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