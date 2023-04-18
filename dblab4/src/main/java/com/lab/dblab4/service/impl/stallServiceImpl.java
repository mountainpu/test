package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.entity.stall;
import com.lab.dblab4.mapper.stallMapper;
import com.lab.dblab4.service.stallService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stallServiceImpl implements stallService {
    @Resource
    stallMapper stallMapper;
    @Override
    public boolean add(stall stall) {
        stallMapper.insert(stall);
        return true;
    }

    @Override
    public boolean update(stall stall) {
        stallMapper.update(stall);
        return true;
    }

    @Override
    public stall find(stall stall) {
        return null;
    }

    @Override
    public stall findByUserId(int user_id) {
        return stallMapper.findByUserId(user_id);
    }

    @Override
    public List<stall> findByCanteen(canteen canteen) {
        return stallMapper.findByCanteen(canteen);
    }

    @Override
    public stall findById(int stall_id) {
        stall stall = new stall();
        stall.setStall_id(stall_id);
        return stallMapper.find(stall);
    }
}
