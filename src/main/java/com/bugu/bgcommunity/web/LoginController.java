package com.bugu.bgcommunity.web;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.bugu.bgcommunity.service.LoginService;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class LoginController{
    private final AuthRequestFactory factory;
    private final LoginService loginService;

    @GetMapping("/hello")
    public String hello(HttpServletRequest request){
        return "hello";
    }

    @GetMapping
    public List<String> list() {
        return factory.oauthList();
    }

    @GetMapping("/login/{type}")
    public void login(@PathVariable String type,
                      HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @GetMapping("/{type}/callback")
    public AuthResponse login(@PathVariable String type, AuthCallback callback,
                              HttpServletResponse response) throws IOException, ServletException {
        log.info("进入了callback，登录方式：" + type);
        AuthRequest authRequest = factory.get(type);
        AuthResponse authResponse = authRequest.login(callback);
        //使用令牌获取用户数据
        log.info("【response】= {}", JSONUtil.toJsonStr(authResponse));
        String token = UUID.randomUUID().toString();
        //添加服务层，用户记录存在则更新，不存在则创建。
        loginService.insertOrUpdate(authResponse, type, token);
        String new_url = "http://192.168.0.102:3000?token=" + token;
        response.sendRedirect(new_url);
        return authResponse;
    }
}