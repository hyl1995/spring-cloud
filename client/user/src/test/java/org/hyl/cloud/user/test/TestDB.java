package org.hyl.cloud.user.test;

import org.hyl.cloud.user.UserApplication;
import org.hyl.cloud.user.entity.User;
import org.hyl.cloud.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserApplication.class)
public class TestDB {

    @Autowired
    private UserService userService;

    @Test
    public void test(){
        User user = new User();
        user.setName("lynn");
        user.setAge(10);
        try {
            userService.addUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
