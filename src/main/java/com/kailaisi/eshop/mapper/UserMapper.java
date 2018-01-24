package com.kailaisi.eshop.mapper;

import com.kailaisi.eshop.model.User;

/**
 * 测试用户的mapper接口
 */
public interface UserMapper {
    /**
     * 查询测试用户信息
     *
     * @return
     */
    User findUserInfo();
}
