package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.flavour;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface flavourMapper {
    void insert(flavour flavour);
    void update(flavour flavour);
    void delete(flavour flavour);
    flavour find(flavour flavour);
    List<flavour> findByDish(dish dish);
    List<flavour> findByDishId(int dish_id);
}
