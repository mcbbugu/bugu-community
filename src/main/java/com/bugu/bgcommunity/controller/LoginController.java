package com.bugu.bgcommunity.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.entity.User;
import com.bugu.bgcommunity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@CrossOrigin
@RestController
public class LoginController {

    @RequestMapping("/oauth/redirect")
    public ResultDTO githubLogin(@RequestParam("code") String code){
        //获取授权码 -> 请求令牌
        Map<String, Object> params = new HashMap<>();
        params.put("client_id", "15cecaffa2691a8b740e");
        params.put("client_secret", "7102c4988d93b035dd63e8b03f1f6f263779a4bf");
        params.put("code", code);
        String accessToken = HttpUtil.post(
                "https://github.com/login/oauth/access_token", params);
        accessToken = "token " + accessToken.split("&")[0].split("=")[1];

        Console.log(accessToken);
        //使用令牌获取用户数据
        String user = HttpRequest.get("https://api.github.com/user")
                .header("Authorization", accessToken)//头信息，多个头信息多次调用此方法即可
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(user);
        User userbean = JSONUtil.toBean(user, User.class);
        return ResultDTO.ok(userbean);
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/finduser")
    public User GetUser(@RequestParam("id") Integer id){
        User user = userService.getUserById(id);
        return user;
    }
}