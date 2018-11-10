package com.dxb.service;

import com.dxb.entity.User;

import java.util.List;

public interface UserService {
    User findByUsername(String name);
    List<User> findAll();
}
