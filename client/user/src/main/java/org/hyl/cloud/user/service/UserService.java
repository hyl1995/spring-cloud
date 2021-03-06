package org.hyl.cloud.user.service;

import org.hyl.cloud.user.entity.User;
import org.hyl.cloud.user.mapper.test1.UserMapper1;
import org.hyl.cloud.user.mapper.test2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper1 userMapper1;
    @Autowired
    private UserMapper2 userMapper2;

    @Transactional
    public void addUser(User user) throws Exception {
        userMapper1.addUser(user.getName(), user.getAge());
        userMapper2.addUser(user.getName(), user.getAge());
    }
}
