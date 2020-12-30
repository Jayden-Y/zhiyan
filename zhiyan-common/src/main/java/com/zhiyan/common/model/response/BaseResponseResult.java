package com.zhiyan.common.model.response;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *@description 通用响应结果
 *@author Jayden
 **/
@Data
@ToString
@NoArgsConstructor
public class  BaseResponseResult<T> extends ResponseResult {

    T queryResult;
    public BaseResponseResult(ResultCode resultCode, T queryResult) {
        super(resultCode);
        this.queryResult = queryResult;
    }

}
