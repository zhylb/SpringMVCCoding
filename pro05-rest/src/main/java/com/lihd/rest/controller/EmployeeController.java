package com.lihd.rest.controller;

import com.lihd.rest.dao.EmployeeDao;
import com.lihd.rest.pojo.Employee;
import com.sun.javafx.image.IntPixelGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.Collection;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/17 9:40
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @GetMapping("/employee")
    public ModelAndView getAllEmployee(){
        ModelAndView modelAndView = new ModelAndView();

        Collection<Employee> employeeList = employeeDao.getAll();

        modelAndView.addObject("employeeList",employeeList);

        modelAndView.setViewName("employee_list");

        return modelAndView;
    }

    @PostMapping("/employee")
    public ModelAndView addEmployee(Employee employee){
        ModelAndView modelAndView = new ModelAndView();
        employeeDao.save(employee);
        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }


    @GetMapping("/employee/{id}")
    public ModelAndView getEmployeeById(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        Employee employee = employeeDao.get(id);

        modelAndView.addObject("emp",employee);

        modelAndView.setViewName("employee_update");

        return modelAndView;

    }

    @PutMapping("/employee")
    public ModelAndView updateEmployee(Employee employee){
        ModelAndView modelAndView = new ModelAndView();

        employeeDao.save(employee);

        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }

    @DeleteMapping("/employee/{id}")
    public ModelAndView deleteEmployeeById(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView();

        employeeDao.delete(id);

        modelAndView.setViewName("redirect:/employee");
        return modelAndView;
    }

}
