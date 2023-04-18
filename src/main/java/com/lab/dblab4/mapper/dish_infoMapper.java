package com.lab.dblab4.mapper;

import com.lab.dblab4.entity.dish_info;
import com.lab.dblab4.entity.stall;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface dish_infoMapper {
    List<dish_info> findAll();
    dish_info findById(int dish_id);
    List<dish_info> search(String content);
    List<dish_info> findAllByStall(stall stall);
}
