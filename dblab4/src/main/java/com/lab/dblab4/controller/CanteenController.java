package com.lab.dblab4.controller;
import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.service.addressService;
import com.lab.dblab4.service.canteenService;
import com.lab.dblab4.service.impl.addressServiceImpl;
import com.lab.dblab4.service.impl.canteenServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CanteenController {

    @Resource
    canteenService canteenService = new canteenServiceImpl();
    @Resource
    addressService addressService = new addressServiceImpl();

    @RequestMapping("uploadCanteen")
    public String uploadCanteen(){
        return "uploadCanteen";
    }

    @RequestMapping("uploadingCanteen")
    public String uploadingCanteen(canteen canteen, @Param("address_name") String address_name, HttpServletRequest request){
        address address = new address();
        address.setAddress_name(address_name);
        addressService.add(address);
        canteen.setUser_id((Integer) request.getSession().getAttribute("user_id"));
        canteen.setAddress_id(address.getAddress_id());
        canteenService.add(canteen);
        return "redirect:uploadCanteen?accept";
    }

    @RequestMapping("updateCanteen/{id}")
    public String updateCanteen(@PathVariable("id") int id, Model model, HttpServletRequest request){
        canteen canteen = new canteen();
        canteen.setCanteen_id(id);
        canteen real = canteenService.find(canteen);
        model.addAttribute("canteen", real);
        address address = addressService.findById(real.getAddress_id());
        model.addAttribute("address", address);
        request.getSession().setAttribute("address", address);
        return "updateCanteen";
    }

    @RequestMapping("updatingCanteen")
    public String updatingCanteen(canteen canteen, @Param("address_name") String address_name, HttpServletRequest request){
        address address = (address) request.getSession().getAttribute("address");
        address.setAddress_name(address_name);
        addressService.update(address);
        canteenService.update(canteen);
        return "redirect:updateCanteen/"+canteen.getCanteen_id()+"?accept";
    }

    @RequestMapping("findAllCanteen")
    @ResponseBody
    public List<canteen> findAllCanteen(){
        return canteenService.findAll();
    }

}
