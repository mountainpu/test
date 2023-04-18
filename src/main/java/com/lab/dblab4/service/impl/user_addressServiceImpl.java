package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.entity.user_address;
import com.lab.dblab4.mapper.user_addressMapper;
import com.lab.dblab4.service.user_addressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class user_addressServiceImpl implements user_addressService {

    @Resource
    user_addressMapper userAddressMapper;
    @Override
    public boolean add(user_address user_address) {
        userAddressMapper.insert(user_address);
        return true;
    }

    @Override
    public boolean update(user_address user_address) {
        return false;
    }

    @Override
    public boolean delete(user_address user_address) {
        return false;
    }

    @Override
    public List<address> findAllByUser(user user) {
        return userAddressMapper.findAllByUser(user);
    }
}
