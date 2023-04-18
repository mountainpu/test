package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.entity.user;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface canteenMapper {
    void insert(canteen canteen);
    void delete(canteen canteen);
    void update(canteen canteen);
    canteen find(canteen canteen);
    List<canteen> findAll();
    List<canteen> myCanteen(int user_id);
}
