package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.user;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper {
    void insert(user user);
    void update(user user);
    user findByUsername(String username);
    user find(user user);
}
