package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.dish;
import com.lab.dblab4.entity.flavour;
import com.lab.dblab4.mapper.flavourMapper;
import com.lab.dblab4.service.flavourService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class flavourServiceImpl implements flavourService {
    @Resource
    flavourMapper flavourMapper;
    @Override
    public boolean add(flavour flavour) {
        flavourMapper.insert(flavour);
        return true;
    }

    @Override
    public List<flavour> findByDish(dish dish) {
       return flavourMapper.findByDish(dish);
    }

    @Override
    public List<flavour> findByDishId(int dish_id) {
        return flavourMapper.findByDishId(dish_id);
    }

    @Override
    public boolean delete(flavour flavour) {
        flavourMapper.delete(flavour);
        return true;
    }

    @Override
    public boolean update(flavour flavour) {
        flavourMapper.update(flavour);
        return true;
    }

    @Override
    public flavour findById(int flavour_id) {
        flavour flavour = new flavour();
        flavour.setFlavour_id(flavour_id);
        return flavourMapper.find(flavour);
    }
}
