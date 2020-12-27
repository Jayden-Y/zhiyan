package com.zhiyan.user.service;

import com.zhiyan.model.user.ext.UserExt;

/**
 * 业务层接口
 *
 * @author Jayden
 * @description TODO
 **/

public interface UserService {

    /**
     * 判断用户是否存在
     * @param data
     * @param datatype
     * @return com.zhiyan.model.response.BaseResponseResult<java.lang.Boolean>
     */
    public Boolean dataCheck(String data, Integer datatype);


    /**
     * 根据用户账号查询用户信息
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    public UserExt getUserExt(String username);
}