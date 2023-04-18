package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.dish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface dishMapper {
    void insert(dish dish);
    void update(dish dish);
    void delete(dish dish);
    dish find(dish dish);
    dish findById(int dish_id);
    List<dish> findAll();
    void updateInventory(@Param("dish_id") int dish_id, @Param("inventory") int inventory);
}
