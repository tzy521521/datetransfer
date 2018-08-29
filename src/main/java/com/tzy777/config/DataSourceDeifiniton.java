package com.tzy777.config;

import lombok.Data;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 14:37
 */
@Data
public class DataSourceDeifiniton {
    private String id;
    private String dbType;
    private String url;
    private String username;
    private String password;
    private String driverClass;
}
