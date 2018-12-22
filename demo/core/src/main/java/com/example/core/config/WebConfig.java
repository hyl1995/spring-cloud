package com.example.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.core.apiconf.CustomRequestMappingHandlerMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class WebConfig extends WebMvcConfigurationSupport {

    @Bean
    public DemoInterceptor interceptor(){
        return new DemoInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new DemoInterceptor());
    }

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }

    @Value("${appKey}")
    private String appKey;
    @Value("${appSecret}")
    private String appSecret;
    @Value("${bucket}")
    private String bucket;
    @Value("${endPoint}")
    private String endPoint;

    @Bean
    public ParamConfig aliyun(){
        return ParamConfig.options()
                .setAppKey(appKey)
                .setAppSecret(appSecret)
                .setBucket(bucket)
                .setEndPoint(endPoint)
                .build();
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }
    @Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1.定义一个消息转换对象convert
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        // 2.添加fastJson配置信息，是否需要格式化
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

        // 3.在convert添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);

        // 4.将convert添加到converters中
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
