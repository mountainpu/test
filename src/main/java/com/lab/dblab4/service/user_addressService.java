package com.lab.dblab4.service;

import com.lab.dblab4.entity.address;
import com.lab.dblab4.entity.user;
import com.lab.dblab4.entity.user_address;

import java.util.List;

public interface user_addressService {
    boolean add(user_address user_address);
    boolean update(user_address user_address);
    boolean delete(user_address user_address);
    List<address> findAllByUser(user user);
}
