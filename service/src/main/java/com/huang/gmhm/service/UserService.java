package com.huang.gmhm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.gmhm.model.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    //用户登录
    Map<String , Object> login(String phoneNumber, String password);

    //用户注册
    Map<String, Object> register(String userName, String phoneNumber, String password, Integer sex);

    //根据用户ID号查询用户信息
    User getUserById(String userId);

    //获取用户列表
    List<User> getUserList();

    //根据Id删除用户
    int deleteUserById(String userId);

    //更新用户信息
    Map<String , Object> updateUser(User user);
}
