package com.lab.dblab4.service;

import com.lab.dblab4.entity.address;

public interface addressService {
    boolean add(String address_name);
    boolean add(address address);
    boolean update(address address);
    boolean delete(address address);
    address find(address address);
    address findById(int address_id);
    address findByAddress(String address_name);
}
