package com.lab.dblab4.service;

import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.dish_info;
import com.lab.dblab4.entity.stall;

import java.util.List;

public interface dishService {
    boolean add(dish dish);
    boolean delete(dish dish);
    boolean deleteById(int dish_id);
    boolean update(dish dish);
    dish find(dish dish);
    dish findById(int dish_id);
    List<dish_info> findLatest();
    List<dish_info> findAllByStall(stall stall);
    List<dish_info> search(String content);
    void updateInventory(int dish_id, int inventory);
}
