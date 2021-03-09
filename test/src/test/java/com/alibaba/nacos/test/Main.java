package com.alibaba.nacos.test;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;

public class Main {
    // Properties/Switch
    private static String config = "DefaultValue";

    public static void main(String[] args) throws NacosException {
        String dataId = "cipher-kms-aes-128-abc";
        String group = "DEFAULT_GROUP";

        Properties properties = new Properties();
        properties.put("endpoint", "addr-pre.acm.aliyun.com");
        properties.put("namespace", "2ae51efc-32ab-430d-9361-ef8cba9bb240");
        // Access ACM with instance RAM role: https://help.aliyun.com/document_detail/72013.html
        // properties.put("ramRoleName", "$ramRoleName");
        String ak = args[0];
        String sk = args[1];
        properties.put("accessKey", ak);
        properties.put("secretKey", sk);
        properties.put("keyId", "alias/acs/acm");
        properties.put("regionId", "cn-shanghai");
        ConfigService configService = NacosFactory.createConfigService(properties);
//        boolean res = configService.publishConfig(dataId, group, "1111");
//        System.out.println(res);
        // Get configuration proactively
        String content = configService.getConfig(dataId, group, 6000);
        System.out.println(content);
        String content1 = configService.getConfig("cipher-kms-aes-128-123", "DEFAULT_GROUP", 6000);
        System.out.println(content1);
    }
}
