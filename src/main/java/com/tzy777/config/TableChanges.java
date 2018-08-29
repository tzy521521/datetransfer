package com.tzy777.config;

import lombok.Data;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 15:04
 */
@Data
public class TableChanges {
    private String columnName;
    private String targetType;
    private String srcType;
    private String handlerRef;
}
