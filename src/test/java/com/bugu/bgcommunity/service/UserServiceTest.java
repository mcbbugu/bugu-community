package com.bugu.bgcommunity.service;

import com.bugu.bgcommunity.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * .
 * Created by mcbbugu
 * 2019-11-17 13:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findUserBy() {
        User userBy = userService.findUserBy("fcad2009-8005-4b06-9422-2ca5e77fcd1c");
        System.out.println(userBy);
    }
}