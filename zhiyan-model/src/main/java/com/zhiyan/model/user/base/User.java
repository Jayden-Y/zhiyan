package com.zhiyan.model.user.base;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户
 * @author Jayden
 * @description TODO
 **/

@Data
@ToString
@Entity
@Table(name="zy_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    private String username;
    private String password;

    private String name;
    @Column(name = "data_type")
    private String dataType;//注册校验时使用，区分数据类型
    private String type;//角色类型，0超级管理员，1管理员，2教学管理员，3教师，4学生
    private String birthday;
    private String userpic;//头像
    private String sex;
    private String email;
    private String phone;
    private String status;//状态
    @Column(name="create_time")
    private Date createTime;
    @Column(name="update_time")
    private Date updateTime;
}
