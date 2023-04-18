package com.lab.dblab4.controller;

import com.lab.dblab4.entity.*;
import com.lab.dblab4.service.*;
import com.lab.dblab4.service.impl.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {
    @Resource
    _orderService orderService = new _orderServiceImpl();
    @Resource
    order_dishService orderDishService = new order_dishServiceImpl();
    @Resource
    dishService dishService = new dishServiceImpl();
    @Resource
    stallService stallService = new stallServiceImpl();
    @Resource
    canteenService canteenService = new canteenServiceImpl();
    @Resource
    flavourService flavourService = new flavourServiceImpl();
    @Resource
    categoryService categoryService = new categoryServiceImpl();
    @Resource
    user_addressService userAddressService = new user_addressServiceImpl();
    @RequestMapping("confirmOrder")
    public String confirmOrder(order_dish order_dish, Model model, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        int dish_id = order_dish.getDish_id();
        int flavour_id = order_dish.getFlavour_id();
        dish dish = dishService.findById(dish_id);
        flavour flavour = flavourService.findById(flavour_id);
        category category = categoryService.findById(dish.getCategory_id());
        stall stall = stallService.findById(category.getStall_id());
        canteen canteen = canteenService.findById(stall.getCanteen_id());
        user user = new user();
        user.setUser_id(user_id);
        List<address> addressList = userAddressService.findAllByUser(user);
        model.addAttribute("dish", dish);
        model.addAttribute("flavour", flavour);
        model.addAttribute("category", category);
        model.addAttribute("stall", stall);
        model.addAttribute("canteen", canteen);
        model.addAttribute("addressList", addressList);
        return "confirmOrder";
    }
    @RequestMapping("confirmingOrder")
    public String confirmingOrder(_order order, order_dish order_dish, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        order.setUser_id(user_id);
        order.setOrder_status((short) 1);
        orderService.add(order);
        order_dish.setOrder_id(order.getOrder_id());
        orderDishService.add(order_dish);
        return "confirmedOrder";
    }
    @RequestMapping("updatingOrderStatus1/{id}")
    @ResponseBody
    public boolean updatingOrder1(@PathVariable("id") int id){
        orderService.updateAcceptTime(id);
        List<order_dish> order_dishList = orderDishService.findAllByOrderId(id);
        for (order_dish orderDish : order_dishList) {
            dish dish = dishService.findById(orderDish.getDish_id());
            dishService.updateInventory(dish.getDish_id(), dish.getInventory() - orderDish.getDish_number());
        }
        return true;
    }
    @RequestMapping("updatingOrderStatus2/{id}")
    @ResponseBody
    public boolean updatingOrder2(@PathVariable("id") int id){
        orderService.updateSendTime(id);
        return true;
    }
    @RequestMapping("updatingOrderStatus3/{id}")
    @ResponseBody
    public boolean updatingOrder3(@PathVariable("id") int id){
        orderService.updateArriveTime(id);
        return true;
    }
}
