package com.zhiyan.common.model.response;

import lombok.ToString;

/**
 * 封装返回值状态码
 */
@ToString
public enum CommonCode implements ResultCode {

    SUCCESS(true, 10000, "操作成功！"),
    FAIL(false, 10001, "操作失败！"),
    INVALID_PARAM(false, 10002, "非法参数！");

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


