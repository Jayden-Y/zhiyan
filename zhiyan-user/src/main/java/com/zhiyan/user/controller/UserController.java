package com.zhiyan.user.controller;

import com.zhiyan.api.user.UserControllerApi;
import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.CommonCode;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.model.user.base.User;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.model.user.response.UserResponseResult;
import com.zhiyan.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户注册及信息查询
 *
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
    public ResponseResult sendVerificatonCode(@RequestParam("phone") String phone) {

        return userService.sendVerificatonCode(phone);
    }

    /**
     * 用户注册
     *
     * @param user 使用校验
     * @param code
     * @return com.zhiyan.common.model.response.ResponseResult
     */
    @PostMapping("/register")
    public ResponseResult register(@Valid User user, @RequestParam("code") String code) {

        return userService.register(user, code);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return com.zhiyan.common.model.response.ResponseResult
     */
    @PutMapping("/update")
    public ResponseResult update(@RequestBody User user) {
        return userService.update(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return com.zhiyan.common.model.response.ResponseResult
     */
    @DeleteMapping("/delete")
    public ResponseResult delete(@RequestParam("id") String id) {
        return userService.delete(id);
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param phone
     * @return com.zhiyan.model.user.response.UserResponse
     */
    @PostMapping("/getuser")
    public UserResponseResult getUser(@RequestParam("phone") String phone) {
        User user = userService.getUser(phone);
        return new UserResponseResult(CommonCode.SUCCESS, user);
    }

    /**
     * 根据手机号查询用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/getuserext")
    public UserExt getUserext(@RequestParam("username") String username) {

        return userService.getUserExt(username);
    }
}

