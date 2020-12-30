package com.zhiyan.model.user.ext;

import com.zhiyan.model.user.base.Permission;
import com.zhiyan.model.user.base.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * User扩展类
 * @author Jayden
 * @description TODO
 **/
@Data
@ToString
public class UserExt extends User {

    //权限信息
    private List<Permission> permissions;

    //企业信息
    private String companyId;
}
