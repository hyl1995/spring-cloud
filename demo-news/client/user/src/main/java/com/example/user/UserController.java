package com.example.user;

import com.example.core.RedisService;
import com.example.core.Result;
import com.example.core.model.BaseController;
import com.example.core.model.LoginRequest;
import com.example.core.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/user")
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/login")
    public Result<TokenResponse> login(@Valid @RequestBody LoginRequest request, BindingResult result){
        //必须要调用validate方法才能实现输入参数的合法性校验
        validate(result);
        return userService.login(request);
    }
}
