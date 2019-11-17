package com.bugu.bgcommunity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugu.bgcommunity.mapper.UserMapper;
import com.bugu.bgcommunity.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:00
 */
@Slf4j
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findUserBy(Integer id){
        return userMapper.selectById(id);
    }

    public User findUserBy(String token){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}