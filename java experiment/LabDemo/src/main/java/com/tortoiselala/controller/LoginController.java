package com.tortoiselala.controller;

import com.google.gson.Gson;
import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.*;
import com.tortoiselala.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RegInfoService regInfoService;

    @Autowired
    private PatientService patientService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.do", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean doctor = request.getParameter("type") == null ? false : true;
        User user = new User(username, password, doctor);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/static/views/login.jsp");
        if (user.getUserId().length() == 0 || user.getPassword().length() == 0) {
            mav.addObject(this.ERROR_MSG_KEY, "用户名或密码不能为空");
        } else {
            boolean status = this.userService.authCheck(user);
            if (!status) {
                mav.addObject(this.ERROR_MSG_KEY, "用户名或密码错误");
            } else {
                this.userService.updateLastLoginDate(user);
                request.getSession().setAttribute(CommonConstant.USER_CONTEXT, user);
                mav.setViewName("redirect:/main");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/main", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView showMainPage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConstant.USER_CONTEXT);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/static/views/login.jsp");
        if (user != null) {
            if (user.isDoctor()) {
                mav.setViewName("redirect:/doctorMain");
            } else {
                mav.setViewName("redirect:/patientMain");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/doctorMain", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView showDoctorMainPage(HttpServletRequest request) throws Exception {
        User user = (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        if(user == null){
            throw new Exception("user is null, while try to get patient list");
        }
        List<PatientUnitForShow> patientList = this.doctorService.getPatientList(user);
        List<IncomeUnitForShow> incomeList = this.doctorService.getIncomeList();

        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/static/views/doctor/patient-and-income-list.jsp");
        mav.addObject(CommonConstant.PATIENT_LIST, patientList);
        mav.addObject(CommonConstant.INCOME_LIST, incomeList);
        return mav;
    }


    @RequestMapping(value = "/patientMain", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView showPatientMainPage(HttpServletRequest request) throws Exception {

        User user = (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        if(user == null) {
            throw new Exception("user is null, while try to get patient list");
        }

        List<Department> departmentList = this.departmentService.getALl();
        List<Doctor> doctorList = this.doctorService.getDoctorListExceptPassword();
        List<RegInfo> regInfoList = this.regInfoService.getAll();

        boolean hashCharge = this.patientService.getCharge(user) > 20;

        Gson gson = new Gson();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("forward:/static/views/patient/main.jsp");
        mav.addObject(CommonConstant.DEPARTMENT_LIST, gson.toJson(departmentList));
        mav.addObject(CommonConstant.DOCTOR_LIST, gson.toJson(doctorList));
        mav.addObject(CommonConstant.REG_INFO_LIST, gson.toJson(regInfoList));
        mav.addObject(CommonConstant.IS_CHARGE_ENOUGH, gson.toJson(hashCharge));

        return mav;
    }
}
