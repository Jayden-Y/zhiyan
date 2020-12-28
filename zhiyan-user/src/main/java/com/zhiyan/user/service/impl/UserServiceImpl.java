package com.zhiyan.user.service.impl;

import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.CommonCode;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.common.utils.NumberUtils;
import com.zhiyan.model.user.base.User;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.model.user.response.UserCode;
import com.zhiyan.user.dao.UserMapper;
import com.zhiyan.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 业务层
 *
 * @author Jayden
 * @description TODO
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    //设置前缀，区分不同业务的短信验证码
    private static final String KEY_PREFIX = "user_verificaton_code:";

    /**
     * 判断用户是否存在
     *
     * @param data 参数：用户名或者手机号
     * @param type 参数类型：1：用户名；2：手机号
     * @return java.lang.Boolean
     */
    public BaseResponseResult<Boolean> dataCheck(String data, int type) {

        User user = new User();

        if (type == 1) {
            user.setUsername(data);
        } else if (type == 2) {
            user.setPhone(data);
        } else {
            log.error("输入的数据类型错误：{}", type);
            return new BaseResponseResult(UserCode.USER_TYPE_ERROR, false);
        }

        try {
            if (userMapper.selectCount(user) == 0) {
                return new BaseResponseResult(CommonCode.SUCCESS, true);
            }
        } catch (Exception e) {

            log.error("数据库查找用户失败");
        }

        return new BaseResponseResult(CommonCode.FAIL, false);
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return java.lang.Boolean
     */
    @Override
    public ResponseResult sendVerificatonCode(String phone) {

        if (phone == null) {
            new ResponseResult(UserCode.USER_PASSWORD_NONE);
        }
        //1.生成验证码
        String code = NumberUtils.generateCode(6);

        //2.发送短信
        try {
            HashMap<String, String> msg = new HashMap<>();
            msg.put("phone", phone);
            msg.put("code", code);

            //2.1 将手机号和验证码组成的集合放入到rabbitmq队列中
            amqpTemplate.convertAndSend("zhiyan.sms.exchange", "verifycode.sms", msg);

            //2.2 将验证码和手机号存入redis,设置5分钟过期
            redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 2, TimeUnit.MINUTES);

            return ResponseResult.SUCCESS();
        } catch (Exception e) {
            log.error("短信发送失败[ phone：{}， code：{}]", phone, code);
            return new ResponseResult(UserCode.USER_SMS_FAIL);
        }
    }

    /**
     * 查询用户信息
     *
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    public UserExt getUserExt(String username) {
        return null;
    }

}
