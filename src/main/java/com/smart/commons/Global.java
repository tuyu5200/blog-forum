package com.smart.commons;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author walker
 * @version 1.0
 * @className Global
 * @date 2018/1/19 14:54
 * @description: 用于获取配置文件中的信息
 */
public class Global {

    private static Global global = new Global();

    private Global() {

    }

    public static Global getInstance() {
        return global;
    }

    /**
     * 获取配置文件（默认文件名 jeesite.properties)
     *
     * @return
     */
    public static Properties getProperties() {
        return getProperties("jeesite.properties");
    }

    private static Properties getProperties(String name) {
        Resource resource = new ClassPathResource(name);
        Properties properties = null;
        try {
            properties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            //异常处理存储
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * 通过key获取properties文件中的值
     *
     * @param key key
     * @return
     */
    public static String getConfig(String key) {
        return (String) getProperties().get(key);
    }

    /**
     * 更新配置
     *
     * @param key
     * @param value
     */
    public static void updateConfig(String key, String value) {
        getProperties().setProperty(key, value);
    }

    public static void main(String[] args) {
        String config = getConfig("jdbc.url");
        System.out.println(config);
    }

}
