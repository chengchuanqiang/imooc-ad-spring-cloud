package com.imooc.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Description: zuul启动类
 * @Author: ChengChuanQiang
 * @Date: 2019/5/3 12:10
 */
@EnableZuulProxy
@SpringCloudApplication
public class ZuulGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulGateWayApplication.class, args);
    }
}
