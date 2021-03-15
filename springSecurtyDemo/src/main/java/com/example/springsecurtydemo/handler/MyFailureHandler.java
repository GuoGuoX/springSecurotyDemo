package com.example.springsecurtydemo.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录失败拦截器
public class MyFailureHandler implements AuthenticationFailureHandler {
    private String url ;

    public MyFailureHandler(String url){
        this.url = url;
    }
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //执行登录失败逻辑代码
        //
        System.out.println("登录失败执行登录逻辑");
        httpServletResponse.sendRedirect(url);
    }
}
