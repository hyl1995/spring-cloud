package com.example.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.core.Result;
import com.example.core.config.ParamConfig;
import com.example.core.apiconf.ApiVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
//@ApiVersion(1)
//@RequestMapping("/client/{version}")
public class HelloController {
//    @Resource
//    private ParamConfig paramConfig;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public Result home() {
        JSONObject json = new JSONObject();
        json.put("result", "hi! Here is port:" +port);
        Map<String, Object> map = new HashMap<>();
        map.put("result", "hi! Here is port:" +port);
        return new Result(true, map);
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public Result index(){
        JSONObject json = new JSONObject();
        json.put("result", "这里是服务提供中心!");
        Map<String, Object> map = new HashMap<>();
        map.put("result", "这里是服务提供中心!");
        return new Result(true, map);
    }
}
