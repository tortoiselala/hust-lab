package com.tortoiselala.dao.impl;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.Doctor;
import com.tortoiselala.entity.PatientUnitForShow;
import com.tortoiselala.entity.User;
import com.tortoiselala.mapper.DoctorMapper;
import com.tortoiselala.utils.MybatisUtil;
import com.tortoiselala.utils.Tools;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.Doc;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorDao implements com.tortoiselala.dao.IDoctorDao {
    @Override
    public boolean checkAuthorityOfDoctor(User user) {
        String userId = user.getUserId();
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            String password = doctorMapper.getPasswordByDoctorID(userId);
            if (password.equals(user.getPassword())) {
                return true;
            }
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
        return false;
    }

    @Override
    public void updateDoctorLastLoginDate(User user, Date date) {
        SqlSession session = null;
        try {
            session = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            Map<String, String> map = new HashMap<>();
            map.put(CommonConstant.LAST_VISITED, Tools.getFormatedDate(date));
            map.put(CommonConstant.USER_ID, user.getUserId());
            doctorMapper.updateLastLoginDate(map);
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

    @Override
    public List<PatientUnitForShow> getPatientListByDoctor(User user) {
        SqlSession session = null;
        List<PatientUnitForShow> list = null;
        try {
            session = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            list = doctorMapper.getRegistrationInformation(user.getUserId());
            boolean type = getDoctorType(user);
            for (PatientUnitForShow e : list) {
                e.setDoctor(type);
            }
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
        return list;
    }

    @Override
    public boolean getDoctorType(User user) {
        SqlSession session = null;
        boolean type = false;
        try {
            session = MybatisUtil.getSqlSession();
            DoctorMapper doctorMapper = session.getMapper(DoctorMapper.class);
            type = doctorMapper.getTypeByDoctorID(user.getUserId());
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
        return type;
    }

    @Override
    public String getDoctorNameByID(String doctorID) {
        SqlSession session = null;
        String name = null;
        try {
            session = MybatisUtil.getSqlSession();
            DoctorMapper mapper = session.getMapper(DoctorMapper.class);
            name = mapper.getNameByDoctorID(doctorID);
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
        return name;
    }

    @Override
    public String getDepartmentIDByID(String id) {
        SqlSession sqlSession = null;
        String departmentID = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
            departmentID = mapper.getDepartmentIDByDoctorID(id);
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
        return departmentID;
    }

    public List<Doctor> getAll() {
        SqlSession sqlSession = null;
        List<Doctor> doctorList = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
            doctorList = mapper.getDoctorList();
            sqlSession.commit();
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
        return doctorList;
    }

    public Doctor getDoctorByDoctorID(String id) {
        SqlSession sqlSession = null;
        Doctor doctor = null;
        try {
            sqlSession = MybatisUtil.getSqlSession();
            DoctorMapper mapper = sqlSession.getMapper(DoctorMapper.class);
            doctor = mapper.getDoctorByDoctorID(id);
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
        return doctor;
    }
}
