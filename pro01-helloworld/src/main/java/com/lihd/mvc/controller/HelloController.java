package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 别忘了四种
 * Controller
 * Service
 * Repository
 *
 *
 *
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/14 16:19
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index(){
        System.out.println("hello world");

        return "index";
    }


    @RequestMapping("/persist")
    public String  persist(){

        return "persist";
    }
}
