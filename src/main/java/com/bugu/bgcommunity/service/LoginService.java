package com.bugu.bgcommunity.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bugu.bgcommunity.mapper.UserMapper;
import com.bugu.bgcommunity.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * .
 * Created by mcbbugu
 * 2019-11-07 21:53
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginService{

    private final UserMapper userMapper;

    public User insertOrUpdate(AuthResponse response, String type, String token){
        JSONObject json = JSONUtil.parseObj(response.getData());
        if(null != response){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("open_id", json.get("uuid").toString());
            User user = userMapper.selectOne(queryWrapper);
            if(ObjectUtil.isNull(user)){
                //将用户信息存入数据库
                user = new User();
                user.setNickName(json.get("nickname").toString());
                user.setOpenId(json.get("uuid").toString());
                user.setOpenType(type);
                user.setToken(token);
                user.setAvatarUrl(json.get("avatar").toString());
                int insert = userMapper.insert(user);
                if(insert == 1){
                    log.info("用户登录，插入用户记录成功 😀, 受影响条数: {}", insert);
                }else{
                    log.info("用户登录，插入用户记录失败 😭, 受影响条数: {}", insert);
                }
                return user;
            }
            else{
                user.setNickName(json.get("nickname").toString());
                user.setAvatarUrl(json.get("avatar").toString());
                user.setToken(token);
                int update = userMapper.updateById(user);
                if(update == 1){
                    log.info("用户登录，更新用户记录成功 😀, 受影响条数: {}", update);
                }else{
                    log.info("用户登录，更新用户记录失败 😭, 受影响条数: {}", update);
                }
                return user;
            }
        }
        return null;
    }
}