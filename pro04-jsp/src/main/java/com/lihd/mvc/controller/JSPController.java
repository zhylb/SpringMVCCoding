package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/17 0:10
 */
@Controller
public class JSPController {
    @RequestMapping("/")
    public String index(){

        return "index";
    }
}
