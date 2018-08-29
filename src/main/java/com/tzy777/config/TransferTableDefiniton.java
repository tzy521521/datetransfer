package com.tzy777.config;

import lombok.Data;

import java.util.List;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 15:11
 */
@Data
public class TransferTableDefiniton {
    private String id;
    private String targetDataSourceRef;
    private String table;
    private String beanClass;
    private String taskImplClass;
    private List<SrcData> srcs;
    private List<TableChanges> changes;
}
