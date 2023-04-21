package com.example.demo.service.impl;

import com.example.demo.dao.AdminUserMapper;
import com.example.demo.entity.AdminUser;
import com.example.demo.service.AdminUserService;
import com.example.demo.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        return adminUserMapper.login(userName, passwordMd5);
    }
}
