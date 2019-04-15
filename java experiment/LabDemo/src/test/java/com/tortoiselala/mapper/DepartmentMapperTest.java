package com.tortoiselala.mapper;

import com.tortoiselala.entity.Department;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.List;

public class DepartmentMapperTest {

    private static SqlSessionFactory sqlSessionFactory = null;

    @Before
    public void init() {
        sqlSessionFactory = MybatisUtil.getSqlSessionFactory();
    }

    @Test
    public void TestGetDepartmentNameByDepartmentID() {
        SqlSession session = null;
        String departmentID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
            String departmentName = departmentMapper.getDepartmentNameByDepartmentID(departmentID);
            Assert.assertTrue("TestGetDepartmentNameByDepartmentID", departmentName.equals("外科"));
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
    public void TestGetDepartmentPinyinByDepartmentID() {
        SqlSession session = null;
        String departmentID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
            String departmentPinyin = departmentMapper.getDepartmentPinyinByDepartmentID(departmentID);
            Assert.assertTrue("TestGetDepartmentNameByDepartmentID", departmentPinyin.equals("WK"));
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Test
    public void TestGetDepartmentByDepartmentID() {
        SqlSession session = null;
        String departmentID = "000001";
        try {
            session = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
            Department department = departmentMapper.getDepartmentByDepartmentID(departmentID);
            Assert.assertTrue("TestGetDepartmentByDepartmentID", department.equals(new Department("000001", "外科", "WK")));
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Test
    public void TestGetDepartmentList() {
        helper(true);
        SqlSession session = null;
        try {
            session = sqlSessionFactory.openSession();
            DepartmentMapper departmentMapper = session.getMapper(DepartmentMapper.class);
            List<Department> list = departmentMapper.getDepartmentList();
            for (Department e : list) {
                System.out.println(e);
            }
        } catch (Exception e) {
            session.rollback();
            System.out.println(e.getMessage());
        } finally {
            if (session != null) {
                session.close();
            }
        }
        helper(false);
    }



    public static void helper(boolean type) {
        if (type) {
            System.out.println("----------- test begin -----------");
        } else {
            System.out.println("----------- test end -------------");
        }
    }
}
