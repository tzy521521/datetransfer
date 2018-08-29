package com.tzy777.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 15:06
 */
@Data
@AllArgsConstructor
public class ErrorMsg {
    private String tableName;
    private String errorMsg;
}
