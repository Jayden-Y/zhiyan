package com.zhiyan.user.dao;

import com.zhiyan.model.user.base.User;
import org.apache.ibatis.annotations.Delete;
import tk.mybatis.mapper.common.Mapper;

/**
  *@Description: 用户
  *@author Jayden
  **/
public interface UserMapper extends Mapper<User>{

    /**
     * [zx]删除中间表对应数据
     * @param cid
     * @param id
     */
    @Delete("delete from zy_user_role where user_id=#{id}")
    void deleteUserRoleByID(String id);
}
