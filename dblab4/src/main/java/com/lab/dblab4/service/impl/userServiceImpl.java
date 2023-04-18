package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.user;
import com.lab.dblab4.mapper.userMapper;
import com.lab.dblab4.service.userService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements userService {

    @Resource
    userMapper userMapper;

    @Override
    public boolean register(user user) {
        userMapper.insert(user);
        return true;
    }

    @Override
    public user login(user user) {
        return userMapper.findByUsername(user.getUsername());
    }

    @Override
    public boolean update(user user) {
        return false;
    }

    @Override
    public user findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public user find(user user) {
        return userMapper.find(user);
    }

    @Override
    public user findById(int user_id) {
        user user = new user();
        user.setUser_id(user_id);
        return userMapper.find(user);
    }
}
