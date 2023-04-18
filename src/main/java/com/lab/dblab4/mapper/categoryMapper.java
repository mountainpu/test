package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.category;
import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.stall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface categoryMapper {
    void insert(category category);
    void delete(category category);
    void updateCategoryName(category category);
    void insertByName(String category_name);
    List<category> findAllByStall(stall stall);
    category findById(int category_id);
}
