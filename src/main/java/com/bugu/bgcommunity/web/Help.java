package com.bugu.bgcommunity.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * .
 * Created by mcbbugu
 * 2019-11-16 00:18
 */
@Controller
public class Help {

    @RequestMapping("/help")
    public String help(HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("token", "2313");
        cookie.setPath("/");
        cookie.setMaxAge(30 * 24 * 60 * 60);
        response.addCookie(cookie);
        response.sendRedirect("http://192.168.0.102:3000");
        return "help";
    }
}