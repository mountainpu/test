package com.lab.dblab4.controller;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.service.*;
import com.lab.dblab4.service.impl.userServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegisterController {
    @Resource
    userService userService = new userServiceImpl();

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("registering")
    public String registering(user user){
        if (userService.register(user)){
            return "redirect:register?accept";
        }
        return "redirect:register?error";
    }

}
