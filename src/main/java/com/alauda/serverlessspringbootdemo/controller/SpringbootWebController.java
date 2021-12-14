package com.alauda.serverlessspringbootdemo.controller;


import com.alauda.serverlessspringbootdemo.constants.Constants;
import com.alauda.serverlessspringbootdemo.entity.UserInfo;
import com.alauda.serverlessspringbootdemo.util.NameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName SpringbootWebController
 * @Description TODO
 * @Author zhuangxinjie
 * @Date 10:06 上午 2021/12/4
 **/
@RequestMapping("/v1/web")
@RestController
@Slf4j
public class SpringbootWebController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AsyncRestTemplate asyncRestTemplate;

    @GetMapping("/userinfo")
    public String getUser(@RequestParam("type") String type){
        String host = String.format(Constants.RABBITMQ_URL,type);
        return restTemplate.getForObject(host,String.class);
    }

    @GetMapping("/userinfo/async")
    public String getUserInfoAsyncRest(@RequestParam("type") String type){
        String host = String.format(Constants.RABBITMQ_URL,type);
        ListenableFuture<ResponseEntity<String>> entity = asyncRestTemplate.getForEntity(host,String.class);
        entity.addCallback(
                new SuccessCallback<ResponseEntity<String>>() {
                    @Override
                    public void onSuccess(ResponseEntity<String> result) {
                        log.info("success:"+result.getBody());
                    }
                }, new FailureCallback() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("fail" + ex.getCause());
                    }
                }
        );
       return "SUCCESS!";
    }

    @GetMapping("/userinfo")
    public UserInfo getUserInfo(){
        return NameUtil.getUserInfo();
    }
}
