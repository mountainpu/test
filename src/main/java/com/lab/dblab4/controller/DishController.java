package com.lab.dblab4.controller;

import com.lab.dblab4.entity.*;
import com.lab.dblab4.mapper.dish_infoMapper;
import com.lab.dblab4.service.categoryService;
import com.lab.dblab4.service.dishService;
import com.lab.dblab4.service.flavourService;
import com.lab.dblab4.service.impl.*;
import com.lab.dblab4.service.stallService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Controller
public class DishController {
    @Resource
    dishService dishService = new dishServiceImpl();
    @Resource
    categoryService categoryService = new categoryServiceImpl();
    @Resource
    flavourService flavourService = new flavourServiceImpl();
    @Resource
    stallService stallService = new stallServiceImpl();
    @Resource
    dish_infoMapper dishInfoMapper;
    @RequestMapping("myDish")
    public String myDish(Model model, HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        String user_type = (String) request.getSession().getAttribute("user_type");
        if (!user_type.equals("stall")) {
            return "unauthorized";
        }
        stall real = stallService.findByUserId(user_id);
        if (real != null) {
            List<dish_info> dishList = dishService.findAllByStall(real);
            for (dish_info dishInfo : dishList) {
                dishInfo.setFlavours(flavourService.findByDishId(dishInfo.getDish_id()));
            }
            model.addAttribute("dishList", dishList);
            model.addAttribute("dish_page", true);
            return "userspace";
        }
        else return "redirect:home?noStall";
    }

    @RequestMapping("uploadDish")
    public String uploadDish(Model model, HttpServletRequest request){
        if (getCategoryList(model, request)) {
            return "unauthorized";
        }
        return "uploadDish";
    }
    @RequestMapping("dishInfo/{id}")
    public String dishInfo(@PathVariable("id") int id, Model model){
        dish_info dish_info = dishInfoMapper.findById(id);
        model.addAttribute("dish", dish_info);
        dish dish = new dish();
        dish.setDish_id(id);
        List<flavour> flavourList = flavourService.findByDish(dish);
        model.addAttribute("flavourList", flavourList);
        return "dishInfo";
    }

    @RequestMapping("uploadingDish")
    public String uploadingDish(dish dish,
                                 @Param("category_name")String category_name,
                                 @Param("flavour") String[] flavour,
                                 HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        category category = new category();
        // 若category是新增的
        if (category_name != null){
            stall stall = stallService.findByUserId(user_id);
            category.setCategory_name(category_name);
            category.setStall_id(stall.getStall_id());
            categoryService.add(category);
            dish.setCategory_id(category.getCategory_id());
        }
        dishService.add(dish);
        // 处理flavour
        for (String s : flavour) {
            flavour f = new flavour();
            f.setDish_id(dish.getDish_id());
            f.setFlavour_desp(s);
            flavourService.add(f);
        }
        return "redirect:uploadDish?accept";
    }

    @RequestMapping("updateDish/{id}")
    public String updateDish(@PathVariable("id")int id, Model model, HttpServletRequest request){
        if (getCategoryList(model, request)) {
            return "unauthorized";
        }
        dish dish = dishService.findById(id);
        if (dish == null){
            return "error/404";
        }
        model.addAttribute("dish", dish);
        List<flavour> flavourList = flavourService.findByDish(dish);
        model.addAttribute("flavourList", flavourList);
        request.getSession().setAttribute("flavourList", flavourList);
        request.getSession().setAttribute("category_id", dish.getCategory_id());
        return "updateDish";
    }
    @RequestMapping("deleteDish/{id}")
    @ResponseBody
    public boolean deleteDish(@PathVariable("id") int id){
        return dishService.deleteById(id);
    }

    private boolean getCategoryList(Model model, HttpServletRequest request) {
        if (request.getSession().getAttribute("user_id") == null){
            return true;
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        stall stall = stallService.findByUserId(user_id);
        List<category> categoryList = categoryService.findAllByStall(stall);
        model.addAttribute("categoryList", categoryList);
        return false;
    }
    @RequestMapping("dishInfo")
    public String dishInfo(){
        return "dishInfo";
    }

    @RequestMapping("updatingDish")
    public String updatingDish(dish dish,
                                @Param("category_name")String category_name,
                                @Param("flavour") String[] flavour,
                                HttpServletRequest request){
        if (request.getSession().getAttribute("user_id") == null){
            return "unauthorized";
        }
        int user_id = (int) request.getSession().getAttribute("user_id");
        int category_id = (int)request.getSession().getAttribute("category_id");
        dish.setCategory_id(category_id);
        // 若category是新增的
        if (category_name != null){
            stall stall = stallService.findByUserId(user_id);
            category category = new category();
            category.setCategory_id(category_id);
            category.setCategory_name(category_name);
            category.setStall_id(stall.getStall_id());
            categoryService.add(category);
        }
        request.removeAttribute("category_id");
        dishService.update(dish);
        // 比对flavourList
        Object object = request.getSession().getAttribute("flavourList");
        List<flavour> old = new ArrayList<>();
        if (object instanceof List<?> obj) {
            for (Object o : obj) {
                old.add((flavour) o);
            }
        }
        request.removeAttribute("flavourList");
        List<String> _new = new ArrayList<>(Arrays.asList(flavour));
        ListIterator<String> iterator = _new.listIterator();
        while (iterator.hasNext()){
            String f = iterator.next();
            if (!f.startsWith("%%a%%")){
                old.removeIf(flavour1 -> flavour1.getFlavour_id() == Integer.parseInt(f));
                iterator.remove();
            }
        }
        if (old.size() == _new.size()){
            for (int i = 0; i < old.size(); i++) {
                old.get(i).setFlavour_desp(_new.get(i).substring(5));
            }
        }
        else if (old.size() > _new.size()){
            for (int i = 0; i < _new.size(); i++) {
                old.get(i).setFlavour_desp(_new.get(i).substring(5));
            }
            for (int i = _new.size(); i < old.size(); i++) {
                flavourService.delete(old.get(i));
            }
        }
        else {
            for (int i = 0; i < old.size(); i++) {
                old.get(i).setFlavour_desp(_new.get(i).substring(5));
            }
            for (int i = old.size(); i < _new.size(); i++) {
                flavour f = new flavour();
                f.setDish_id(dish.getDish_id());
                f.setFlavour_desp(_new.get(i).substring(5));
                flavourService.add(f);
            }
        }
        for (flavour f : old) {
            flavourService.update(f);
        }

        return "redirect:updateDish/"+dish.getDish_id();
    }
}
