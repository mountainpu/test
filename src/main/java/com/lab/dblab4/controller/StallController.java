package com.lab.dblab4.controller;

import com.lab.dblab4.entity.stall;
import com.lab.dblab4.service.stallService;
import com.lab.dblab4.service.impl.stallServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StallController {
    @Resource
    stallService stallService = new stallServiceImpl();

    @RequestMapping("uploadStall")
    public String uploadStall(){
        return "uploadStall";
    }

    @RequestMapping("uploadingStall")
    public String uploadingStall(stall stall, HttpServletRequest request){
        if ((int) request.getSession().getAttribute("user_id") == 0){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        stall.setUser_id(user_id);
        stallService.add(stall);
        return "redirect:uploadStall?accept";
    }

    @RequestMapping("updatingStall")
    public String updatingStall(stall stall){
        System.out.println(stall);
        stallService.update(stall);
        return "redirect:myStall?accept";
    }

    @RequestMapping("myStall")
    public String myStall(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("user_id") == null) {
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        String user_type = (String) request.getSession().getAttribute("user_type");
        if (!user_type.equals("stall")) {
            return "unauthorized";
        }
        stall real = stallService.findByUserId(user_id);
        if (real != null) {
            model.addAttribute("stall", real);
            return "updateStall";
        }
        else return "redirect:home?noStall";
    }
}
