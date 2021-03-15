package com.oauth2.springsecurityoauth2demo.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/detail")
    @ResponseBody
    public Object getDetail(Authentication authentication){//authentication身份认证
        Object principal = authentication.getPrincipal();
        return principal;
    }

    //解析JWT令牌
    @RequestMapping("/parseJWT")
    @ResponseBody
    public Object parseJWT(HttpServletRequest request){//authentication身份认证
        String token = request.getHeader("Authorization");
        token = token.substring(token.lastIndexOf("bearer")+7);
        Claims body = Jwts.parser()
                .setSigningKey("test_key" .getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        return body;
        /*{
            "enhancer": "im is Enhancer info",
            "user_name": "admin",
            "scope": [
                "all"
            ],
            "exp": 1615167318,
            "authorities": [
                "admin"
            ],
            "jti": "827b8edd-aee4-4cac-8c1a-5758d3e3f2ac",
            "client_id": "client"
        }*/
    }
}
