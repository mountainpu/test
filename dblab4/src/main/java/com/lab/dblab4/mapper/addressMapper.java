package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface addressMapper {
    void insertByName(String address_name);
    void insert(address address);
    void update(address address);
    void delete(address address);
    address find(address address);
    address findById(int address_id);
    address findByAddress(String address_name);
}
