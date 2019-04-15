package com.tortoiselala.controller;

import com.tortoiselala.constant.CommonConstant;
import com.tortoiselala.entity.User;
import com.tortoiselala.service.impl.DoctorService;
import com.tortoiselala.service.impl.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserActionController extends BaseController {

    @Autowired
    RegistrationService registrationService;

    @RequestMapping("/registration.do")
    public ModelAndView registration(HttpServletRequest request){
        Map<String, String> map = new HashMap<>();
        map.put(CommonConstant.DEPARTMENT_NAME, request.getParameter("departmentName"));
        map.put(CommonConstant.DOCTOR_NAME, request.getParameter("doctorName"));
        map.put(CommonConstant.QUEUE_TYPE, request.getParameter("queueType"));
        map.put(CommonConstant.QUEYE_NAME, request.getParameter("queueName"));
        map.put(CommonConstant.INPUT_CHARGE, request.getParameter("inputCharge"));
        map.put(CommonConstant.AMOUNT_DUE, request.getParameter("amountDue"));
        map.put(CommonConstant.AMOUNT_CHARGE, request.getParameter("amountCharge"));

        User user = (User)request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
        ModelAndView mav = new ModelAndView();
        try {
            registrationService.registration(map, user);
        } catch (Exception e) {
//            e.printStackTrace();
            mav.addObject(CommonConstant.MESSAGE, e.getMessage());
        }

        mav.setViewName("forward:/static/views/patient/registration-success.jsp");
        return mav;
    }
}
