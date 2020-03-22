package com.example.demo_sanm.configbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.client.loadbalancer.LoadBalanced;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/16.
 * @by: DELL)
 */

@Configuration
public class BeanConfig {

    @Bean
   // @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
