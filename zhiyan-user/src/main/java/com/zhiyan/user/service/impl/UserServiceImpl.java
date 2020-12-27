package com.zhiyan.user.service.impl;

import com.zhiyan.model.user.base.User;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.user.dao.UserMapper;
import com.zhiyan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务层
 * @author Jayden
 * @description TODO
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;

    /**
     * 判断用户是否存在
     * @param data 参数：用户名或者手机号
     * @param type 参数类型：1：用户名；2：手机号
     * @return java.lang.Boolean
     */
    public Boolean dataCheck(String data, Integer type) {

        User user = new User();

        if (type == 1) {
            user.setUsername(data);
        } else if (type == 2) {
            user.setPhone(data);
        } else {
            return null;
        }

        return this.userMapper.selectCount(user) == 0;
    }

    /**
     * 查询用户信息
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    public UserExt getUserExt(String username) {
        return null;
    }

}
