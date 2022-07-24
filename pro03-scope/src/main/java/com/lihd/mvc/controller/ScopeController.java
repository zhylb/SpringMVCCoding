package com.lihd.mvc.controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/16 15:49
 */
@Controller
public class ScopeController {

    @RequestMapping("/testServletAPI")
    public String testServletAPI(HttpServletRequest request){
        request.setAttribute("requestScope","servletAPI");
        return "persist";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView mav = new ModelAndView();

        //1设置model 向域对象中存放数据
        mav.addObject("requestScope","ModelAndView");
        //2设置view 设置视图名称
        mav.setViewName("persist");

        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model){
        model.addAttribute("requestScope","Model");
        return "persist";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String ,Object> map){
        map.put("requestScope","Map");
        return "persist";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        BindingAwareModelMap bindingAwareModelMap;
        modelMap.addAttribute("requestScope","ModelMap");
        return "persist";
    }
    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("sessionScope","session");
        return "persist";
    }


    @RequestMapping("testApplication")
    public String testApplication(HttpSession session){
        ServletContext application = session.getServletContext();
        application.setAttribute("applicationScope","application");
        return "persist";
    }






}
