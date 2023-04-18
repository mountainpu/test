package com.lab.dblab4.service;

import com.lab.dblab4.entity._order;
import com.lab.dblab4.entity.order_dish;

import java.util.List;

public interface order_dishService {
    List<order_dish> findAllByOrderId(int order_id);
    order_dish find(order_dish order_dish);
    boolean delete(order_dish order_dish);
    boolean add(order_dish order_dish);
    boolean deleteAllByOrderId(int order_id);
}
