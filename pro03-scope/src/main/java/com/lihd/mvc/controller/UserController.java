package com.lihd.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：葬花吟留别1851053336@qq.com
 * @description：TODO
 * @date ：2022/4/16 23:01
 */
@Controller
public class UserController {

    // get对应查询全部数据
    @GetMapping("/user")
    public String getAllUser(){

        System.out.println("UserController.getAllUser");

        return "persist";
    }

    // get对应查询一条数据
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id){
        System.out.println("id = " + id);
        System.out.println("UserController.getUserById");
        return "persist";
    }

    //post 对应添加数据
    @PostMapping("/user")
    public String addUser(String username,String password){
        System.out.println("UserController.addUser");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "persist";
    }

    //put 对应修改更新数据
    @PutMapping("/user")
    public String updateUser(String username,String password){
        System.out.println("UserController.updateUser");
        System.out.println("username = " + username);
        System.out.println("password = " + password);
        return "persist";
    }

    //delete 对应删除数据
    @DeleteMapping("/user/{id}")
    public String deleteUserById(@PathVariable Integer id){
        System.out.println("UserController.deleteUserById");
        System.out.println("id = " + id);
        return "persist";
    }

}
