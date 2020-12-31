package com.zhiyan.model.user.base;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户
 *
 * @author Jayden
 * @description TODO
 **/
@Entity
@Data
@ToString
@Table(name = "zy_user")
public class User {

    @Id
    @Column(length = 32)
    private String id;

    @Length(min = 6, max = 30, message = "用户名长度在6-30位之间")
    private String username;

    @Length(min = 6, max = 30, message = "用户名长度在6-30位之间")
    private String password;

    private String name;
    private String type;//角色类型，0超级管理员，1管理员，2教学管理员，3教师，4学生
    private String birthday;
    private String userpic;//头像
    private String sex;
    private String email;

    @Pattern(regexp = "^1[356789]\\d{9}$", message = "手机号格式不正确")//使用正则表达式
    private String phone;
    private String status;//状态
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
