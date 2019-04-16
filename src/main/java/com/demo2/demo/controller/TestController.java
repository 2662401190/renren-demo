package com.demo2.demo.controller;


import com.demo2.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * @author 贺威
 * @create 2019-04-12 16:46
 */
@Controller
public class TestController {

    @GetMapping("/login")
    public String  login(){

        return "login";
    }

    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @GetMapping("/logout")
    public  String logout(){

        Subject subject= SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public  String admin(){

        return "admin success";
    }

    @RequestMapping("/unauthorized")
    public String unauthorized(){

        return "unauthorized";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(){

        return "edit success";
    }

    @PostMapping("/loginUser")
    public String loginUser(@RequestParam("username")  String username,
                            @RequestParam("password")  String password,
                            HttpSession session){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject= SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user=(User) subject.getPrincipal();
            //  把登录用户存到session
            session.setAttribute("user", user);

            return "index";
        }catch (Exception e){
            return "login";
        }

    }
}
