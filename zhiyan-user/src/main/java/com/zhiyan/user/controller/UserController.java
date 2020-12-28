package com.zhiyan.user.controller;

import com.zhiyan.api.user.UserControllerApi;
import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户注册及信息查询
 * @author Jayden
 * @description TODO
 **/


@RestController
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Autowired
    UserService userService;

    /**
     * 判断用户是否存在
     */
    @GetMapping("/check/{data}/{datatype}")
    public BaseResponseResult<Boolean> dataCheck(@PathVariable("data") String data, @PathVariable("datatype") int datatype) {

        return userService.dataCheck(data, datatype);
    }

    /**
     * 发送验证码
     *
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    @PostMapping("/code")
    public ResponseResult sendVerificatonCode(@RequestParam("phone")String phone) {

        return userService.sendVerificatonCode(phone);
    }

    /**
     * 根据用户账号查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/getuserext")
    public UserExt getUserext(@RequestParam("username") String username) {

        return userService.getUserExt(username);
    }
}

