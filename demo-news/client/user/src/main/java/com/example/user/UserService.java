package com.example.user;

import com.example.core.Result;
import com.example.core.model.BaseService;
import com.example.core.model.LoginRequest;
import com.example.core.model.TokenResponse;
import com.example.core.model.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserService extends BaseService {

    @Autowired
    private UserMapper userMapper;

    public Result<TokenResponse> login(LoginRequest request) {
        List<UserBean> userList = userMapper.selectUser(request.getMobile(), request.getPassword());
        if (null != userList && userList.size() > 0) {
            String token = getToken(request.getMobile(), request.getPassword());
            TokenResponse response = new TokenResponse();
            response.setToken(token);
            return new Result<>(response);
        } else {
            return new Result<>(false, "手机号或密码输入不正确！");
        }
    }
}
