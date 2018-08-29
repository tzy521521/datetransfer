package com.tzy777.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lipu@csii.com.cn
 * @date 2018/8/24 15:20
 */
public class Config {
    private static final Config CONFIG = new Config();
    private static final Map<String,Object> commonParameters = new HashMap<>();

    public static Config initConfig(Map<String,String> paramterMap){
        if(commonParameters.isEmpty() && !paramterMap.isEmpty() ){
            commonParameters.putAll(paramterMap);
            return CONFIG;
        }
        return CONFIG;
    }
    //TODO: 这里需要做同步的处理。需要优化，暂时先这样处理；
    public synchronized Object getParameter(String key){
        return commonParameters.get(key);
    }
    public synchronized void setParameter(String key, Object value){
        commonParameters.put(key, value);
    }

    @Override
    public String toString() {
        return  commonParameters.toString();
    }
}
