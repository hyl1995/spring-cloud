package com.example.feign;

import com.example.core.Result;
import org.springframework.stereotype.Component;

@Component
public class ApiServiceError implements ApiService {
    @Override
    public Result index() {
        return new Result(false, "index服务发生故障");
    }

    @Override
    public Result hello() {
        return new Result(false, "hello服务发生故障");
    }
}
