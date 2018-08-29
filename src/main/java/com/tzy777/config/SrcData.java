package com.tzy777.config;

import lombok.Data;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 15:00
 */
@Data
public class SrcData {
    private String querySql;
    private String srcDataSourceRef;
    private String keyColumn;
    private Boolean isMainSrc;
}
