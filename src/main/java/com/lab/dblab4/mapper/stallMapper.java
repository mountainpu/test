package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.entity.stall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface stallMapper {
    void insert(stall stall);
    void update(stall stall);
    stall find(stall stall);
    stall findByUserId(int user_id);
    List<stall> findByCanteen(canteen canteen);
}
