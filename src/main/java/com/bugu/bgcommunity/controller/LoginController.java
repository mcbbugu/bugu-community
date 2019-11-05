package com.bugu.bgcommunity.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.bugu.bgcommunity.mapper.UserMapper;
import com.bugu.bgcommunity.model.entity.User;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController {
    private final AuthRequestFactory factory;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<String> list() {
        return factory.oauthList();
    }

    @GetMapping("/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        log.info("进入登录方法");
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/{type}/callback")
    public AuthResponse login(@PathVariable String type, AuthCallback callback) {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(response));

        if(ObjectUtil.isNotNull(response)){
            //将用户信息存入数据库
            User user = new User();
            JSONObject json = JSONUtil.parseObj(response.getData());
            user.setNickName(json.get("nickname").toString());
            user.setOpenId(json.get("uuid").toString());
            user.setOpenType(type);
            user.setToken(UUID.randomUUID().toString());
            user.setAvatarUrl(json.get("avatar").toString());

            //添加服务层，用户记录存在则更新，不存在则创建。
            int insert = userMapper.insert(user);
            if(insert == 1){
                log.info("用户登录，插入用户记录成功 😀, 受影响条数: {}", insert);
            }else{
                log.info("用户登录，插入用户记录失败 😭, 受影响条数: {}", insert);
            }
        }
        return response;
    }
}