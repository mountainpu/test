package com.lab.dblab4.service;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.canteen;

import java.util.List;

public interface canteenService {
    List<canteen> findAll();
    List<canteen> findAllByUserId(int user_id);
    canteen find(canteen canteen);
    boolean add(canteen canteen);
    boolean update(canteen canteen);
    List<address> findMyCanteenAddress(int user_id);
    canteen findById(int canteen_id);
}
