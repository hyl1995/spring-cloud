package com.example.feign;

import com.example.core.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "client", fallback = ApiServiceError.class)
//@FeignClient(name = "client")
@Service
//@RequestMapping(value = "/client/v1")
public interface ApiService {
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    Object index();

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    Object hello();
}
