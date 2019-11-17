package com.bugu.bgcommunity.controller;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.model.entity.User;
import com.bugu.bgcommunity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

    private final UserService userService;

    @GetMapping("/find")
    public ResultDTO findBy(@RequestParam("token") String token,
                            HttpServletResponse response){

        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        cookie.setDomain("192.168.0.102");
        response.addCookie(cookie);

        User user = userService.findUserBy(token);
        return ResultDTO.ok(user);
    }

    @GetMapping("/refresh")
    public ResultDTO refresh(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Short i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("token")){
                    User user = userService.findUserBy(cookies[i].getValue());
                    return ResultDTO.ok(user);
                }
            }
        }
        return ResultDTO.error("未登录");
    }
}