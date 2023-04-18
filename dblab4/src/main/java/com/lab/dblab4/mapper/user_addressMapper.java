package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.entity.user_address;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface user_addressMapper {
    void insert(user_address user_address);
    void delete(user_address user_address);
    List<address> findAllByUser(user user);
}
