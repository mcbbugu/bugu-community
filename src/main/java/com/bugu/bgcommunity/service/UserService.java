package com.bugu.bgcommunity.service;

import com.bugu.bgcommunity.mapper.UserMapper;
import com.bugu.bgcommunity.model.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:00
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User getUserById(Integer id){
        return userMapper.selectById(id);
    }
}