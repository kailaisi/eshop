package com.kailaisi.eshop.control;

import com.kailaisi.eshop.model.User;
import com.kailaisi.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller
public class UserControl {
    @Autowired
    private UserService userService;
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public User getUserInfo() {
        return userService.findUserInfo();
    }
}
