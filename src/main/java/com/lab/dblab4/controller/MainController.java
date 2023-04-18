package com.lab.dblab4.controller;

import com.lab.dblab4.entity.*;
import com.lab.dblab4.service.*;
import com.lab.dblab4.service.impl.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Resource
    dishService dishService = new dishServiceImpl();
    @Resource
    canteenService canteenService = new canteenServiceImpl();
    @Resource
    stallService stallService = new stallServiceImpl();
    @Resource
    addressService addressService = new addressServiceImpl();
    @Resource
    _orderService orderService = new _orderServiceImpl();
    @Resource
    order_dishService orderDishService = new order_dishServiceImpl();
    @Resource
    flavourService flavourService = new flavourServiceImpl();
    @Resource
    userService userService = new userServiceImpl();
    @RequestMapping({"/", "index"})
    public String index(Model model){
        List<dish_info> dishList = dishService.findLatest();
        model.addAttribute("dishList", dishList);
        return "index";
    }
    @RequestMapping("searching")
    public String searching(@Param("content") String content, Model model){
        System.out.println(content);
        List<dish_info> dishList = dishService.search(content);
        model.addAttribute("dishList", dishList);
        return "search";
    }

    @RequestMapping("home")
    public String home(Model model, HttpServletRequest request, @Param("menu")Integer menu){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        String user_type = (String) request.getSession().getAttribute("user_type");
        if (user_type.equals("canteen")){
            List<canteen> canteenList = canteenService.findAllByUserId(user_id);
            model.addAttribute("canteenList", canteenList);
            List<List<stall>> stallList = new ArrayList<>();
            List<address> addressList = new ArrayList<>();
            for (canteen canteen : canteenList) {
                addressList.add(addressService.findById(canteen.getAddress_id()));
                stallList.add(stallService.findByCanteen(canteen));
            }
            model.addAttribute("stallList", stallList);
            model.addAttribute("addressList", addressList);
        }
        else if (user_type.equals("user")){
            List<_order> orderList;
            if (menu == null){
                orderList = orderService.findAllByUserId(user_id);
            }
            else{
                orderList = orderService.findAllByUserIdStatus(user_id, menu);
            }
            List<List<order_dish>> order_dishList = new ArrayList<>();
            List<List<dish>> dishList = new ArrayList<>();
            List<List<flavour>> flavourList = new ArrayList<>();
            List<stall> stallList = new ArrayList<>();
            List<address> addressList = new ArrayList<>();
            for (_order order : orderList){
                order_dishList.add(orderDishService.findAllByOrderId(order.getOrder_id()));
                stallList.add(stallService.findById(order.getStall_id()));
                addressList.add(addressService.findById(order.getAddress_id()));
            }
            for (List<order_dish> list : order_dishList){
                List<flavour> f = new ArrayList<>();
                List<dish> d = new ArrayList<>();
                for (order_dish order_dish : list) {
                    f.add(flavourService.findById(order_dish.getFlavour_id()));
                    d.add(dishService.findById(order_dish.getDish_id()));
                }
                dishList.add(d);
                flavourList.add(f);
            }
            model.addAttribute("orderList", orderList);
            model.addAttribute("stallList", stallList);
            model.addAttribute("dishList", dishList);
            model.addAttribute("flavourList", flavourList);
            model.addAttribute("addressList", addressList);
            model.addAttribute("order_dishList", order_dishList);
        }
        else {
            stall stall = stallService.findByUserId(user_id);
            List<_order> orderList;
            if (menu == null){
                orderList = orderService.findAllByStall(stall);
            }
            else{
                orderList = orderService.findAllByStallStatus(stall, menu);
            }
            List<List<order_dish>> order_dishList = new ArrayList<>();
            List<List<dish>> dishList = new ArrayList<>();
            List<List<flavour>> flavourList = new ArrayList<>();
            List<user> userList = new ArrayList<>();
            List<address> addressList = new ArrayList<>();
            for (_order order : orderList){
                order_dishList.add(orderDishService.findAllByOrderId(order.getOrder_id()));
                userList.add(userService.findById(order.getUser_id()));
                addressList.add(addressService.findById(order.getAddress_id()));
            }
            for (List<order_dish> list : order_dishList){
                List<flavour> f = new ArrayList<>();
                List<dish> d = new ArrayList<>();
                for (order_dish order_dish : list) {
                    f.add(flavourService.findById(order_dish.getFlavour_id()));
                    d.add(dishService.findById(order_dish.getDish_id()));
                }
                dishList.add(d);
                flavourList.add(f);
            }
            model.addAttribute("orderList", orderList);
            model.addAttribute("userList", userList);
            model.addAttribute("dishList", dishList);
            model.addAttribute("flavourList", flavourList);
            model.addAttribute("addressList", addressList);
            model.addAttribute("order_dishList", order_dishList);
        }
        return "userspace";
    }
}
