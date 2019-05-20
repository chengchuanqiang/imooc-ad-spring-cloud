package com.imooc.ad.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Description: 设置消息转换器
 * @Author: ChengChuanQiang
 * @Date: 2019/5/3 22:14
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        // java对象转换为http消息
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
