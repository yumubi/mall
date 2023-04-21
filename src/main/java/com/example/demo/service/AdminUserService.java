package com.example.demo.service;

import com.example.demo.dao.AdminUserMapper;
import com.example.demo.entity.AdminUser;

public interface AdminUserService {
    AdminUser login(String userName, String password);
}
