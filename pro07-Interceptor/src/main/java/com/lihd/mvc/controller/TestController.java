package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/18 15:01
 */
@Controller
public class TestController {
    //Ant风格的访问路径
    @RequestMapping("/**/testInterceptor")
    public String toPersist(){
        return "persist";
    }

    @RequestMapping("/testExceptionResolver")
    public String testExceptionResolver(){
        int a = 1/0;
        return "persist";
    }
}
