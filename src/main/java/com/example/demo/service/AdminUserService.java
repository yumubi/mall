package com.example.demo.service;


import com.example.demo.entity.AdminUser;

public interface AdminUserService {
    AdminUser login(String userName, String password);
    AdminUser getUserDetailById(Integer loginUserId);

    Boolean updatePassword(Integer loginUserId, String originPassword, String newPassword);


    Boolean updateName(Integer loginUserId, String loginUserName, String nickName);
}
