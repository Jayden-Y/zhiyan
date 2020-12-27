package com.zhiyan.common.model.response;


/**
 *@description 通用响应结果
 *@author Jayden
 **/
public class  BaseResponseResult<T> extends ResponseResult {

    T queryResult;
    public BaseResponseResult(ResultCode resultCode, T queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }

}
