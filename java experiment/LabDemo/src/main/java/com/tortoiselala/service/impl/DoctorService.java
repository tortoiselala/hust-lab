package com.tortoiselala.service.impl;

import com.tortoiselala.dao.impl.DepartmentDao;
import com.tortoiselala.dao.impl.DoctorDao;
import com.tortoiselala.dao.impl.RegistrationDao;
import com.tortoiselala.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class DoctorService {
    @Autowired
    DoctorDao doctorDao;

    @Autowired
    RegistrationDao registrationDao;

    @Autowired
    DepartmentDao departmentDao;


    public List<PatientUnitForShow> getPatientList(User user){
        return doctorDao.getPatientListByDoctor(user);
    }


    public List<IncomeUnitForShow> getIncomeList(){
        List<Registration> registrationList = registrationDao.getAll();

        Map<String, IncomeUnitForShow> incomeMap = new HashMap<>();
        for(Registration e : registrationList){
            IncomeUnitForShow tmp = null;
            if(incomeMap.containsKey(e.getDoctorID())){
                tmp = incomeMap.get(e.getDoctorID());
            }
            if(tmp == null){
                IncomeUnitForShow newItem = new IncomeUnitForShow();
                newItem.setNum(1);
                newItem.setIncome(e.getFee());
                newItem.setDoctorID(e.getDoctorID());
                newItem.setDoctorName(doctorDao.getDoctorNameByID(e.getDoctorID()));
                newItem.setLabName(departmentDao.getName(doctorDao.getDepartmentIDByID(e.getDoctorID())));
                User user = new User();
                user.setUserId(e.getDoctorID());
                newItem.setType(doctorDao.getDoctorType(user) ? "专家号" : "普通号");

                incomeMap.put(e.getDoctorID(), newItem);
            }else{
                tmp.setIncome(tmp.getIncome() + e.getFee());
                tmp.setNum(tmp.getNum()+ 1);
            }
        }
        List<IncomeUnitForShow> resultList = new LinkedList<>();
        for(Map.Entry<String, IncomeUnitForShow> e : incomeMap.entrySet()){
            resultList.add(e.getValue());
        }
        return resultList;
    }

    public List<Doctor> getDoctorListExceptPassword(){
        List<Doctor> doctorList = this.doctorDao.getAll();
        for(Doctor e : doctorList){
            e.setDoctorPassword("");
        }
        return doctorList;
    }
}
