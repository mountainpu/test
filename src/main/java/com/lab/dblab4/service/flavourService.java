package com.lab.dblab4.service;

import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.flavour;

import java.util.List;

public interface flavourService {
    boolean add(flavour flavour);
    List<flavour> findByDish(dish dish);
    List<flavour> findByDishId(int dish_id);
    boolean delete(flavour flavour);
    boolean update(flavour flavour);
    flavour findById(int flavour_id);
}
