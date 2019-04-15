package com.tortoiselala.dao.impl;

import com.tortoiselala.entity.Department;
import com.tortoiselala.mapper.DepartmentMapper;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class DepartmentDao {
    public String getName(String id) {
        String name = null;
        SqlSession sqlSession = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            name = mapper.getDepartmentNameByDepartmentID(id);
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.rollback();
                sqlSession.close();
            }
        }
        return name;
    }

    public List<Department> getAll() {
        SqlSession sqlSession = null;
        List<Department> departmentList = new LinkedList<Department>();
        try {
            sqlSession = MybatisUtil.getSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            departmentList = mapper.getDepartmentList();
            sqlSession.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.rollback();
                sqlSession.close();
            }
        }
        return departmentList;
    }
}
