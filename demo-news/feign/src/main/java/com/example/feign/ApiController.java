package com.example.feign;

import com.example.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @RequestMapping("/index")
    public String index(){
        Result result = (Result) apiService.index();
        return result.toString();
    }

    @RequestMapping("/hello")
    public String hello(){
        Result result = (Result) apiService.hello();
        return result.toString();
    }
}
