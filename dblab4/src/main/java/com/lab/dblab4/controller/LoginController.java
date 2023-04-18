package com.lab.dblab4.controller;

import com.lab.dblab4.entity.user;
import com.lab.dblab4.service.impl.userServiceImpl;
import com.lab.dblab4.service.userService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {
    @Resource
    userService userService = new userServiceImpl();
    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("logging")
    public String logging(user user, HttpServletRequest request){
        user real = userService.login(user);
        if (real != null){
            request.getSession().setAttribute("username", real.getUsername());
            request.getSession().setAttribute("user_id", real.getUser_id());
            String user_type = "";
            if (real.getUser_type() == 1){
                user_type = "user";
            }
            else if (real.getUser_type() == 2){
                user_type = "canteen";
            }
            else if (real.getUser_type() == 3){
                user_type = "stall";
            }
            request.getSession().setAttribute("user_type", user_type);
            return "redirect:login?accept";
        }
        return "redirect:login?error";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") != null){
            request.getSession().removeAttribute("username");
            request.getSession().removeAttribute("user_id");
            request.getSession().removeAttribute("user_type");
            return "redirect:index?logout=1";
        }
        else {
            return "redirect:index?logout=0";
        }
    }
}
