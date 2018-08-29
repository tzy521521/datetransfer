package com.tzy777.xmlDefinition;

import com.alibaba.fastjson.JSONObject;
import com.tzy777.config.Config;
import com.tzy777.resources.Resource;
import com.tzy777.utils.Xml2JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 16:02
 */
@Slf4j
public class DefaultLoadDefinition implements LoadDefinition{

    private static final String IS_LIST = "is_List";
    private static final String IS_MAP = "is_MAP";
    private static final String TRANSFERDEFINITION = "transferDefinition";
    private static final String DATASOURCES_KEY = "dataSources";
    private static final String DATASOURCE_KEY = "dataSources";
    private static final String TABLES_KEY = "tables";
    private static final String TABLE_KEY = "table";
    private static final String PROPERTIES_KEY = "properties";
    private static final String PROPERTIE_KEY = "propertie";
    private static final String TYPEHANDLERS_KEY = "typeHandlers";
    private static final String TYPEHANDLER_KEY = "typeHandler";

    private JSONObject xmlJSON;
    private Map<String,Object> xmlMap;

    private Config config;

    public DefaultLoadDefinition() {
        log.info("初始化XML任务定义文件加载器完成；" + DefaultLoadDefinition.class.getName());
    }

    @Override
    public void loadXMLDefiniton(Resource resource) throws IOException {
        log.info("开始加载解析XML文件"+resource.getFile().getName());

        xmlJSON = Xml2JsonUtil.xml2Json_staxon(resource);
        xmlMap = (Map<String, Object>) xmlJSON.get(TRANSFERDEFINITION);
        log.debug("将任务定义XML文件转换为JSON："+ xmlMap);

        doLoadXMLDefinition();
    }

    private void doLoadXMLDefinition(){
        log.info("开始将XML文件的定义内容转化对应的bean: ");
        buildCommonConfig();
        log.info("XML文件的定义<properties>(通用配置config)转化为对应的bean完成 ---  config解析完毕，内容为："+config.toString());
    }

    private void buildCommonConfig(){
        Map<String,String> propertiesMap = new HashMap<>();

        
        this.config = Config.initConfig(propertiesMap);
    }
}
