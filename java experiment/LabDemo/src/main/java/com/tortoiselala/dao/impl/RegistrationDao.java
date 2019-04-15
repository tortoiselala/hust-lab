package com.tortoiselala.dao.impl;

import com.tortoiselala.entity.Registration;
import com.tortoiselala.mapper.RegistrationMapper;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RegistrationDao {
    public List<Registration> getAll() {
        SqlSession sqlSession = null;
        List<Registration> list = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            RegistrationMapper mapper = sqlSession.getMapper(RegistrationMapper.class);
            list = mapper.getAll();
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
        return list;
    }

    public String getNextNum() {
        SqlSession session = null;
        String num = null;
        try {
            session = MybatisUtil.getSqlSession();
            RegistrationMapper registrationMapper = session.getMapper(RegistrationMapper.class);
            num = registrationMapper.getNextNum();
            session.commit();
        } catch (Exception e) {
            if (session != null) {
                session.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return num;
    }

    public void insert(Registration param) {
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            RegistrationMapper registrationMapper = session.getMapper(RegistrationMapper.class);
            registrationMapper.insertNewItem(param);
            session.commit();
        } catch (Exception e) {
            if (session != null) {
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
