package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/14 22:28
 */

@Controller
@RequestMapping("/reqMap") //为了以后的测试 先注释一下
public class RequestMappingController {

    // @RequestMapping("/") 如果有相同路径的 直接报错
    public String  testSame(){
        return "index";
    }

    @RequestMapping(
            value = {"persist","per"},
            method = {RequestMethod.GET,RequestMethod.POST}
    )
    public String test02(){
        return "persist";
    }



    @GetMapping("/getMapping")
    public String getMapping(){
        return "persist";
    }

    @DeleteMapping("/deleteMapping")
    public String deleteMapping(){
        return "persist";
    }


    @RequestMapping(
            //这两个值 ： 有一个匹配即可
            value = {"/params","/headers"},
            method = {RequestMethod.GET,RequestMethod.POST},
            //下面的值是必须全部匹配才可
            // username 必须含有username
            // !username 必须没有username
            // username=admin 必须含有username 值必须是admin
            // username!=admin 必须含有username 值不能是 admin
            params = {"username","password!=root"},
            //headers的相关设置 和上面的username差不多
            headers = {"Host=localhost:8080"}


    )
    public String paramsAndHeaders(){

        return "persist";
    }


    @RequestMapping("/test?Ant")
    public String testAnt(){
        return "persist";
    }

    @RequestMapping("/a*a/testAnt")
    public String testAnt1(){
        return "persist";
    }

    @RequestMapping("/**/testAnt")
    public String testAnt2(){
        return "persist";
    }

    @RequestMapping("/login/{id}/{username}")
    public String login(@PathVariable Integer id,@PathVariable String username){
        System.out.println("id = " + id);
        System.out.println("username = " + username);
        return "persist";
    }




}
