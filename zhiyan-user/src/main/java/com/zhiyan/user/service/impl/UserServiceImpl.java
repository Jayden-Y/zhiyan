package com.zhiyan.user.service.impl;

import com.zhiyan.common.exception.ExceptionCast;
import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.CommonCode;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.utils.NumberUtils;
import com.zhiyan.model.user.base.User;
import com.zhiyan.model.user.ext.UserExt;
import com.zhiyan.model.user.response.UserCode;
import com.zhiyan.user.dao.UserMapper;
import com.zhiyan.user.service.UserService;
import com.zhiyan.utils.BCryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.annotation.KeySql;

import java.util.Date;
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
    StringRedisTemplate redisTemplate;

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

        //根据用户名
        if (type == 1) {
            user.setUsername(data);
        } else if (type == 2) {
            //根据手机号
            user.setPhone(data);
        } else {
            log.error("输入的数据类型错误：{}", type);
            ExceptionCast.cast(UserCode.USER_TYPE_ERROR);
        }

        try {
            if (userMapper.selectCount(user) == 0) {
                return new BaseResponseResult(CommonCode.SUCCESS, true);
            }
        } catch (Exception e) {

            log.error("数据库查找用户失败");
            ExceptionCast.cast(CommonCode.DATABASE_LOOKUP_FAIL);
        }

        return new BaseResponseResult(CommonCode.FAIL, false);
    }

    /**
     * 发送验证码
     *
     * @param phone
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

            //2.2 将验证码和手机号存入redis,测试设置6分钟过期
            redisTemplate.opsForValue().set(KEY_PREFIX + phone, code, 6, TimeUnit.MINUTES);
            System.out.println(code);
        } catch (Exception e) {
            log.error("短信发送失败[ phone：{}， code：{}]", phone, code);
            ExceptionCast.cast(UserCode.USER_SMS_FAIL);
        }
        return ResponseResult.SUCCESS();
    }

    /**
     * 用户注册
     *
     * @param user
     * @param code
     * @return com.zhiyan.common.model.response.ResponseResult
     */
    public ResponseResult register(User user, String code) {

        //1.获取redis中验证码
        String redisCode = redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());

        //2.校验验证码
        if (!StringUtils.equals(code, redisCode)) {

            ExceptionCast.cast(UserCode.USER_VERIFYCODE_ERROR);
        }

        //3.密码加密
        String encode = BCryptUtil.encode(user.getPassword());
        user.setPassword(encode);

        //4.可防止恶意注入
        user.setId(null);

        user.setCreateTime(new Date());

        //5.将用户信息添加到数据库
        Boolean flag = null;
        try {
            flag = this.userMapper.insertSelective(user) == 1;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("数据库添加用户或查询失败：{}",flag);
            ExceptionCast.cast(CommonCode.DATABASE_OPERATION_FAIL);
        }
        if (flag) {
            try {
                // 注册成功，删除redis中的记录
                redisTemplate.delete(KEY_PREFIX + user.getPhone());
            } catch (Exception e) {
                log.error("redis删除验证码错误");
            }
        }
        return ResponseResult.SUCCESS();
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
