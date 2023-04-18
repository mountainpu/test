package com.lab.dblab4.service;

import com.lab.dblab4.entity.user;

public interface userService {
    boolean register(user user);
    user login(user user);
    boolean update(user user);
    user findByUsername(String username);
    user find(user user);
    user findById(int user_id);
}
