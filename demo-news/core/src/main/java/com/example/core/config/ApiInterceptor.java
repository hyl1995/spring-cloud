package com.example.core.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ApiInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
    private String salt = "ed4ffcd453efab32";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        logger.info("进入拦截器");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "application/json;charset=utf8");

        StringBuilder urlBuilder = getUrlAuthenticationApi(request);        //这里是MD5加密算法
        String sign = DigestUtils.md5Hex(urlBuilder+salt);
        String signature = request.getHeader("signature");
        logger.info("加密前传入的签名" + signature);
        logger.info("后端加密后的签名" + sign);
        if (sign.equals(signature)) {
            return true;
        } else {            //签名错误
            response.getWriter().print("签名错误");
            response.getWriter().close();
            return false;
        }
    }

    private StringBuilder getUrlAuthenticationApi(HttpServletRequest request) {
        Enumeration<String> paramesNames = request.getParameterNames();
        List<String> nameList = new ArrayList<>();
        nameList.add("token");
        nameList.add("timestamp");
        while (paramesNames.hasMoreElements()) {
            nameList.add(paramesNames.nextElement());
        }
        StringBuilder urlBuilder = new StringBuilder();
        nameList.stream().sorted().forEach(name -> {
            if ("token".equals(name) || "timestamp".equals(name)) {
                if ("token".equals(name) && null == request.getHeader(name)) {
                    return;
                }
                urlBuilder.append('&');
                urlBuilder.append(name).append('=').append(request.getHeader(name));
            } else {
                urlBuilder.append('&');
                urlBuilder.append(name).append('=').append(request.getParameter(name));
            }
        });
        urlBuilder.deleteCharAt(0);
        logger.info("url : " + urlBuilder.toString());
        return urlBuilder;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
