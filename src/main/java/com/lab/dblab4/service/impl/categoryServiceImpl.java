package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.category;
import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.mapper.categoryMapper;
import com.lab.dblab4.service.categoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoryServiceImpl implements categoryService {
    @Resource
    categoryMapper categoryMapper;
    @Override
    public boolean add(category category) {
        categoryMapper.insert(category);
        return true;
    }

    @Override
    public boolean add(String category_name) {
        categoryMapper.insertByName(category_name);
        return true;
    }

    @Override
    public boolean updateCategoryName(category category) {
        categoryMapper.updateCategoryName(category);
        return true;
    }

    @Override
    public boolean delete(category category) {
        categoryMapper.delete(category);
        return true;
    }

    @Override
    public List<category> findAllByStall(stall stall) {
        return categoryMapper.findAllByStall(stall);
    }

    @Override
    public category findById(int category_id) {
        return categoryMapper.findById(category_id);
    }


}
