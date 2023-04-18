package com.lab.dblab4.controller;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.entity.user_address;
import com.lab.dblab4.service.addressService;
import com.lab.dblab4.service.impl.addressServiceImpl;
import com.lab.dblab4.service.impl.userServiceImpl;
import com.lab.dblab4.service.impl.user_addressServiceImpl;
import com.lab.dblab4.service.userService;
import com.lab.dblab4.service.user_addressService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Resource
    userService userService = new userServiceImpl();

    @Resource
    user_addressService userAddressService = new user_addressServiceImpl();

    @Resource
    addressService addressService = new addressServiceImpl();

    @RequestMapping("userinfo")
    public String userinfo(Model model, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int)request.getSession().getAttribute("user_id");
        user user = new user();
        user.setUser_id(user_id);
        user real = userService.find(user);
        model.addAttribute("userinfo", real);
        List<address> addressList = userAddressService.findAllByUser(real);
        request.getSession().setAttribute("addressList", addressList);
        model.addAttribute("addressList", addressList);
        return "updateUser";
    }

    @RequestMapping("updatingUser")
    public String userinfo(user user, @Param("address_name") String[] address_name, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        List<address> addressList = new ArrayList<>();
        for (String s : address_name) {
            address address = new address();
            address.setAddress_name(s);
            addressList.add(address);
        }
        int user_id = (int)request.getSession().getAttribute("user_id");
        user.setUser_id(user_id);
        userService.update(user);
        Object object = request.getSession().getAttribute("addressList");
        List<address> old_address = new ArrayList<>();
        if (object instanceof List<?> obj) {
            for (Object o : obj) {
                old_address.add((address) o);
            }
        }
        request.getSession().removeAttribute("addressList");
        int old_size = old_address.size();
        int new_size = addressList.size();
        if(new_size == old_size){
            for (int i = 0; i < old_size; i++) {
                addressList.get(i).setAddress_id(old_address.get(i).getAddress_id());
            }
        }
        else if (new_size < old_size){
            for (int i = 0; i < new_size; i++) {
                addressList.get(i).setAddress_id(old_address.get(i).getAddress_id());
            }
            for (int i = new_size; i < old_size; i++) {
                addressService.delete(addressList.get(i));
            }
        }
        else {
            for (int i = 0; i < old_size; i++) {
                addressList.get(i).setAddress_id(old_address.get(i).getAddress_id());
            }
            for (int i = old_size; i < new_size; i++){
                user_address user_address = new user_address();
                user_address.setUser_id(user_id);
                addressService.add(addressList.get(i));
                user_address.setAddress_id(addressList.get(i).getAddress_id());
                userAddressService.add(user_address);
            }
        }
        for (address address:addressList) {
            addressService.update(address);
        }
        return "redirect:userinfo?accept";
    }

    @GetMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        return userService.findByUsername(username) == null;
    }
}
