package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.order_dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface order_dishMapper {
    void insert(order_dish order_dish);
    void delete(order_dish order_dish);
    order_dish find(order_dish order_dish);
    List<order_dish> findAllByOrderId(int order_id);
    void deleteAllByOrderId(int order_id);
}
