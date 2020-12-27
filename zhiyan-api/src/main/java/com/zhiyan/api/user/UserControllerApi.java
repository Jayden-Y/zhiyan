package com.zhiyan.api.user;

import com.zhiyan.model.user.ext.UserExt;

/**
 * @author Jayden
 * @description TODO
 **/
public interface UserControllerApi {

    /**
     * 根据用户账号查询用户信息
     * @param username
     * @return
     */
    public UserExt getUserext(String username);
}
