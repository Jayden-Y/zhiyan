package com.zhiyan.api.user;

import com.zhiyan.common.model.response.BaseResponseResult;
import com.zhiyan.common.model.response.ResponseResult;
import com.zhiyan.model.user.ext.UserExt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jayden
 * @description TODO
 **/
@Api(value = "用户中心", description = "用户信息管理")
public interface UserControllerApi {

    /**
     * 判断用户是否存在
     */
    @ApiOperation("校验注册数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "data", value = "注册数据", required = true, paramType = "path", dataType = "String"),
            @ApiImplicitParam(name = "datatype", value = "数据类型", required = true, paramType = "path", dataType = "int")})
    public BaseResponseResult<Boolean> dataCheck(String data, int datatype);

    /**
     * 发送验证码
     *
     * @param username
     * @return com.zhiyan.model.user.ext.UserExt
     */
    @ApiOperation("发送验证码")
    @ApiImplicitParam(name = "phone",value = "手机号",required = true,paramType = "query",dataType = "String")
    public ResponseResult sendVerificatonCode(String phone) ;

    /**
     * 根据用户账号查询用户信息
     *
     * @param username
     * @return
     */
    @ApiOperation("根据用户账号查询用户信息")
    public UserExt getUserext(String username);
}
