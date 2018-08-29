package com.tzy777;

import com.alibaba.fastjson.JSONObject;
import com.tzy777.resources.ClassPathResource;
import com.tzy777.resources.Resource;
import com.tzy777.utils.Xml2JsonUtil;
import com.tzy777.xmlDefinition.DefaultLoadDefinition;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 16:14
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception{
        log.info("使用lomback测试logback");
        Resource resource = new ClassPathResource("TransferDefinitions.xml");

        System.out.println(Xml2JsonUtil.xml2Json_jsonlib(resource));
        System.out.println(Xml2JsonUtil.xml2Json_staxon(resource));
        //System.out.println(Xml2JsonUtil.xml2JsonSting_staxon(resource));

        DefaultLoadDefinition defaultLoadDefinition = new DefaultLoadDefinition();
        defaultLoadDefinition.loadXMLDefiniton(resource);
    }
}
