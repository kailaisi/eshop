package com.kailaisi.eshop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kailaisi.eshop.dao.RedisDao;
import com.kailaisi.eshop.mapper.UserMapper;
import com.kailaisi.eshop.model.User;
import com.kailaisi.eshop.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisDao redisDao;
    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCachedUserInfo() {
        redisDao.set("cached_user","{ \"name\": \"lisi\",\"age\": 20}");
        String userJSON = redisDao.get("cached_user");
        JSONObject jsonObject = JSONObject.parseObject(userJSON);
        User user = new User();
        user.setName(jsonObject.getString("name"));
        user.setAge(jsonObject.getInteger("age"));
        return user;
    }
}
