package com.lihd.mvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/18 18:19
 */
@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public String testExceptionHandler(Exception exception, Model model){

        model.addAttribute("exception",exception);
        System.out.println("ExceptionController.testExceptionHandler");
        return "error";
    }
}
