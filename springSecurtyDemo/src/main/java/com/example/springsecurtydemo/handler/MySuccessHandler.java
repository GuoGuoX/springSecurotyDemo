package com.example.springsecurtydemo.handler;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//自定义登录成功处理器
//@Configuration//不能交给spring管理
//Parameter 0 of constructor in com.example.springsecurtydemo.handler.MySuccessHandler required a bean of type 'java.lang.String' that could not be found.
public class MySuccessHandler  implements AuthenticationSuccessHandler {
    private  String url;
    public MySuccessHandler(String url){
        this.url = url ;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String remoteUser = httpServletRequest.getRemoteAddr();
        System.out.println(remoteUser);
        User credentials = (User) authentication.getPrincipal();
        String username = credentials.getUsername();
        System.out.println(username);
        String password = credentials.getPassword();
        System.out.println(password);
        //打印结果
//        admin
//        null
        //        Collection<GrantedAuthority> authorities = credentials.getAuthorities();

        httpServletResponse.sendRedirect(url);
    }
}
