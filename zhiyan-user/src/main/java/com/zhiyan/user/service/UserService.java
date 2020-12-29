package com.zhiyan.user.service;

import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.model.user.base.User;
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
    public BaseResponseResult<Boolean> dataCheck(String data, int datatype);


    /**
     * 发送验证码，并将验证码存入redis
     * @param phone
     * @return java.lang.Boolean
     */
    public ResponseResult sendVerificatonCode(String phone);

    /**
     * 用户注册功能
     * @param user
     * @param code
     */
    public ResponseResult register(User user, String code);

    /**
     * 根据用户账号查询用户信息
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    public UserExt getUserExt(String username);
}
