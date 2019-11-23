package com.bugu.bgcommunity.core.api;

import com.bugu.bgcommunity.common.ResultDTO;
import com.bugu.bgcommunity.common.annotation.RRestController;
import com.bugu.bgcommunity.core.service.UserService;
import com.bugu.bgcommunity.core.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@Slf4j
@RRestController("/user/")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserApi {

    private final UserService userService;

    @GetMapping("find")
    public ResultDTO findBy(@RequestParam("token") String token,
                            HttpServletResponse response){

        Cookie cookie = new Cookie("bgcommunity-token", token);
        cookie.setPath("/");
        cookie.setDomain("192.168.0.102");
        response.addCookie(cookie);

        User user = userService.findUserBy(token);
        return ResultDTO.ok(user);
    }

    @GetMapping("refresh")
    public ResultDTO refresh(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(null != cookies){
            for(Short i = 0; i < cookies.length; i++){
                if(cookies[i].getName().equals("bgcommunity-token")){
                    User user = userService.findUserBy(cookies[i].getValue());
                    return ResultDTO.ok(user);
                }
            }
        }
        return ResultDTO.error("未登录");
    }
}