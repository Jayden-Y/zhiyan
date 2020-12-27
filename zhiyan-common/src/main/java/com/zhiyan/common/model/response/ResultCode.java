package com.zhiyan.common.model.response;

/**
 * 状态码接口
 */
public interface ResultCode {
    //操作结果,true为成功，false操作失败
    boolean success();

    //操作代码
    int code();

    //提示信息
    String message();

}
