package com.zhiyan.common.model.response;

import lombok.Data;
import lombok.ToString;


/**
 * 响应结果
 */
@Data
@ToString
public class QueryResponseResult<T> extends ResponseResult {

    QueryResult<T> queryResult;

    public QueryResponseResult(ResultCode resultCode, QueryResult queryResult){
        super(resultCode);
       this.queryResult = queryResult;
    }

}
