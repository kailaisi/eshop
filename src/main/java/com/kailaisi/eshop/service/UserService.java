package com.kailaisi.eshop.service;

import com.kailaisi.eshop.model.User;

public interface UserService {
    User findUserInfo();
    User getCachedUserInfo();
}
