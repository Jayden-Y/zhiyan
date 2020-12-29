package com.zhiyan.common.model.response;

import lombok.ToString;

/**
 * 封装返回值状态码
 */
@ToString
public enum CommonCode implements ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    FAIL(false, 10001, "操作失败！"),
    INVALID_PARAM(false, 10002, "非法参数！"),
    DATABASE_LOOKUP_FAIL(false, 10003, "数据库查找失败！"),
    DATABASE_ADD_FAIL(false, 10004, "数据库添加失败！"),
    DATABASE_OPERATION_FAIL(false, 10005, "数据库操作失败！"),
    SERVER_ERROR(false,99999,"抱歉，系统繁忙，请稍后重试！");;

    //操作是否成功
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private CommonCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


}


