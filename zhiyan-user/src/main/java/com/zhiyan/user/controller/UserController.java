package com.zhiyan.user.controller;

import com.zhiyan.api.user.UserControllerApi;
import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.CommonCode;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户信息查询
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
    @GetMapping(value = "check/{data}/{datatype}")
    public BaseResponseResult<Boolean> checkUser(@PathVariable("data") String data, @PathVariable("datatype") Integer datatype){

        Boolean bool = userService.dataCheck(data,datatype);
        if (bool == null) {
            return new BaseResponseResult(CommonCode.INVALID_PARAM, null);
        }
        return new BaseResponseResult(CommonCode.SUCCESS, bool);
    }

    /**
     * 根据用户账号查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/getuserext")
    public UserExt getUserext(@RequestParam("username") String username) {

        return userService.getUserExt(username);
    }
}

