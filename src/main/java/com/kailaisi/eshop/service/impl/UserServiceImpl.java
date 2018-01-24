package com.kailaisi.eshop.service.impl;

import com.kailaisi.eshop.mapper.UserMapper;
import com.kailaisi.eshop.model.User;
import com.kailaisi.eshop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }
}
