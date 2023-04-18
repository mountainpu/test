package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.dish_info;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.mapper.dishMapper;
import com.lab.dblab4.mapper.dish_infoMapper;
import com.lab.dblab4.service.dishService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class dishServiceImpl implements dishService {
    @Resource
    dishMapper dishMapper;
    @Resource
    dish_infoMapper dishInfoMapper;

    @Override
    public boolean add(dish dish) {
        dishMapper.insert(dish);
        return true;
    }

    @Override
    public boolean delete(dish dish) {
        dishMapper.delete(dish);
        return true;
    }

    @Override
    public boolean deleteById(int dish_id) {
        dish dish = new dish();
        dish.setDish_id(dish_id);
        dishMapper.delete(dish);
        return true;
    }

    @Override
    public boolean update(dish dish)
    {
        dishMapper.update(dish);
        return true;
    }

    @Override
    public dish find(dish dish) {
        return dishMapper.find(dish);
    }

    @Override
    public dish findById(int dish_id) {
        return dishMapper.findById(dish_id);
    }

    @Override
    public List<dish_info> findLatest() {
        return dishInfoMapper.findAll();
    }

    @Override
    public List<dish_info> findAllByStall(stall stall) {
        return dishInfoMapper.findAllByStall(stall);
    }

    @Override
    public List<dish_info> search(String content) {
        return dishInfoMapper.search(content);
    }

    @Override
    public void updateInventory(int dish_id, int inventory) {
        dishMapper.updateInventory(dish_id, inventory);
    }
}
