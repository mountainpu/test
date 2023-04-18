package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity._order;
import com.lab.dblab4.entity.order_dish;
import com.lab.dblab4.mapper.order_dishMapper;
import com.lab.dblab4.service.order_dishService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class order_dishServiceImpl implements order_dishService {
    @Resource
    order_dishMapper orderDishMapper;
    @Override
    public List<order_dish> findAllByOrderId(int order_id) {
        return orderDishMapper.findAllByOrderId(order_id);
    }

    @Override
    public order_dish find(order_dish order_dish) {
        return orderDishMapper.find(order_dish);
    }

    @Override
    public boolean delete(order_dish order_dish) {
        orderDishMapper.delete(order_dish);
        return true;
    }

    @Override
    public boolean add(order_dish order_dish) {
        orderDishMapper.insert(order_dish);
        return true;
    }

    @Override
    public boolean deleteAllByOrderId(int order_id) {
        orderDishMapper.deleteAllByOrderId(order_id);
        return true;
    }
}
