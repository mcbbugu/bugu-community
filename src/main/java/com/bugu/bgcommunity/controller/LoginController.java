package com.bugu.bgcommunity.controller;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 * Created by mcbbugu
 * 2019-10-28 09:42
 */
@CrossOrigin
@RestController
@RequestMapping("/oauth")
public class LoginController {

//    @RequestMapping("/oauth/redirect")
//    public ResultDTO githubLogin(@RequestParam("code") String code){
//        //获取授权码 -> 请求令牌
//        Map<String, Object> params = new HashMap<>();
//        params.put("client_id", "15cecaffa2691a8b740e");
//        params.put("client_secret", "7102c4988d93b035dd63e8b03f1f6f263779a4bf");
//        params.put("code", code);
//        String accessToken = HttpUtil.post(
//                "https://github.com/login/oauth/access_token", params);
//        accessToken = "token " + accessToken.split("&")[0].split("=")[1];
//
//        Console.log(accessToken);
//        //使用令牌获取用户数据
//        String user = HttpRequest.get("https://api.github.com/user")
//                .header("Authorization", accessToken)//头信息，多个头信息多次调用此方法即可
//                .timeout(20000)//超时，毫秒
//                .execute().body();
//        Console.log(user);
//        User userbean = JSONUtil.toBean(user, User.class);
//        return ResultDTO.ok(userbean);
//    }
//
//    @Autowired
//    private UserService userService;
//
//    @RequestMapping("/finduser")
//    public User GetUser(@RequestParam("id") Integer id){
//        User user = userService.getUserById(id);
//        return user;
//    }

    private AuthRequest getAuthRequest(){
        return new AuthGiteeRequest(AuthConfig.builder()
                .clientId("4c8be9b5b8082a6d438005eb2b3f5242e29409d28ab8223e56538c395ec75096")
                .clientSecret("62d0e971800fff4fb694d4d5c1870e8585be489e0c1ef8c7ad8c66c97d518f58")
                .redirectUri("http://127.0.0.1:3000/oauth/callback/gitee")
                .build());
    }
}