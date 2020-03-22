package com.example.demo_sanm.service;

import com.example.demo_sanm.beanentity.HttpBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @单位名称：科大国创—电信资源事业部
 * @创建人：hu.yunhui
 * @创建时间: on 2020/3/16.
 * @by: DELL)
 */
@Service
public class HttclienService {

    @Autowired
    private RestTemplate restTemplate;

    public  String postToSend(HttpBean httpBean){
        //url
        //headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("X-APP-ID",httpBean.getAppId());
        headers.add("X-APP-KEY",httpBean.getAppKey());
        HttpEntity<String> request = new HttpEntity<>(httpBean.getJson(), headers);
        System.out.println("====>"+restTemplate);
        System.out.println("====>"+httpBean);

        ResponseEntity<String> postForEntity = restTemplate.postForEntity(httpBean.getUrl(), request, String.class);
        System.out.println("==postForEntity==>"+postForEntity);

        String body = postForEntity.getBody();
        System.out.println(body);
        return  body;

    }
}
