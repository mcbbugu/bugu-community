package com.bugu.bgcommunity.service;

import com.bugu.bgcommunity.model.entity.User;
import com.bugu.bgcommunity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:00
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserById(Integer id){
        return userMapper.find(id);
    }
}