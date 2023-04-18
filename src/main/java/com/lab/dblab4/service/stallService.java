package com.lab.dblab4.service;

import com.lab.dblab4.entity.canteen;
import com.lab.dblab4.entity.stall;

import java.util.List;

public interface stallService {
    boolean add(stall stall);
    boolean update(stall stall);
    stall find(stall stall);
    stall findByUserId(int user_id);
    List<stall> findByCanteen(canteen canteen);
    stall findById(int stall_id);
}
