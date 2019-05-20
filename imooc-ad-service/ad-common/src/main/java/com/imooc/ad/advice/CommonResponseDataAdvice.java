package com.imooc.ad.advice;

import com.imooc.ad.annotation.IgnoreResponseAdvice;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @Description: 对响应进行统一拦截
 * @Author: ChengChuanQiang
 * @Date: 2019/5/3 21:54
 */
@RestControllerAdvice
public class CommonResponseDataAdvice implements ResponseBodyAdvice<Object> {

    @SuppressWarnings(value = "all")
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {

        // 类上注解 不进行方法统一返回拦截
        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        // 方法上注解 不进行方法统一返回拦截
        if (methodParameter.getMethod().isAnnotationPresent(IgnoreResponseAdvice.class)) {
            return false;
        }

        return true;
    }

    @SuppressWarnings(value = "all")
    @Nullable
    @Override
    public Object beforeBodyWrite(@Nullable Object obj,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        CommonResponse<Object> response = new CommonResponse<>(0, "");

        if (null == obj) {
            return response;
        } else if (obj instanceof CommonResponse) {
            response = (CommonResponse<Object>) obj;
        } else {
            response.setData(obj);
        }

        return response;
    }
}
