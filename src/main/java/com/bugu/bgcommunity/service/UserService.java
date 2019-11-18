package com.bugu.bgcommunity.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugu.bgcommunity.mapper.UserMapper;
import com.bugu.bgcommunity.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 * Created by mcbbugu
 * 2019-11-01 22:00
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserService {

    private final UserMapper userMapper;

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