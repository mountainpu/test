package com.lab.dblab4.service.impl;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.mapper.addressMapper;
import com.lab.dblab4.service.addressService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class addressServiceImpl implements addressService {
    @Resource
    addressMapper addressMapper;

    @Override
    public boolean add(String address_name) {
        addressMapper.insertByName(address_name);
        return true;
    }

    @Override
    public boolean add(address address) {
        addressMapper.insert(address);
        return true;
    }

    @Override
    public boolean update(address address) {
        addressMapper.update(address);
        return true;
    }

    @Override
    public boolean delete(address address) {
        addressMapper.delete(address);
        return true;
    }

    @Override
    public address find(address address) {
        return addressMapper.find(address);
    }

    @Override
    public address findById(int address_id) {
        return addressMapper.findById(address_id);
    }

    @Override
    public address findByAddress(String address_name) {
        return addressMapper.findByAddress(address_name);
    }
}
