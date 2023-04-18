package com.lab.dblab4.service;

import com.lab.dblab4.entity.category;
import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.stall;

import java.util.List;

public interface categoryService {
    boolean add(category category);
    boolean add(String category_name);
    boolean updateCategoryName(category category);
    boolean delete(category category);
    List<category> findAllByStall(stall stall);
    category findById(int category_id);
}
