package com.bugu.bgcommunity.controller;

import cn.hutool.json.JSONUtil;
import com.bugu.bgcommunity.common.ResultBean;
import com.bugu.bgcommunity.model.entity.User;
import com.bugu.bgcommunity.service.LoginService;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
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
public class LoginController{
    private final AuthRequestFactory factory;
    private final LoginService loginService;

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
    public ResultBean login(@PathVariable String type, AuthCallback callback,
                              HttpServletResponse res) {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        log.info("【response】= {}", JSONUtil.toJsonStr(response));

        //添加服务层，用户记录存在则更新，不存在则创建。
        String token = UUID.randomUUID().toString();
        User user = loginService.insert(response, type, token);
        res.addCookie(new Cookie("token", token));
        return new ResultBean(user);
    }
}