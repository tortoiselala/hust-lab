package com.tortoiselala.dao.impl;

import com.tortoiselala.entity.RegInfo;
import com.tortoiselala.mapper.RegInfoMapper;
import com.tortoiselala.mapper.RegistrationMapper;
import com.tortoiselala.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.LinkedList;
import java.util.List;

public class RegInfoDao {
    public List<RegInfo> getALl() {
        SqlSession sqlSession = null;
        List<RegInfo> regInfoList = new LinkedList<>();
        try {
            sqlSession = MybatisUtil.getSqlSession();
            RegInfoMapper mapper = sqlSession.getMapper(RegInfoMapper.class);
            regInfoList = mapper.getAll();sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return regInfoList;
    }

    public double getFeeByID(String id) {
        SqlSession sqlSession = null;
        double fee = 0;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            RegInfoMapper mapper = sqlSession.getMapper(RegInfoMapper.class);
            fee = mapper.getFeeByID(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return fee;
    }

    public String getNameByID(String id) {
        SqlSession sqlSession = null;
        String name = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            RegInfoMapper mapper = sqlSession.getMapper(RegInfoMapper.class);
            name = mapper.getNameByID(id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return name;
    }

    public int getNum(String id) {
        SqlSession sqlSession = null;
        int num = 0;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            RegInfoMapper mapper = sqlSession.getMapper(RegInfoMapper.class);
            num = mapper.getMaxNumberByID(id);
            sqlSession.commit();
        } catch (Exception e) {
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return num;
    }


    public void updateRegistrationNum(String id, int newValue){
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            RegInfoMapper regInfoMapper = session.getMapper(RegInfoMapper.class);
            regInfoMapper.updateRegistrationNum(newValue, id);
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
