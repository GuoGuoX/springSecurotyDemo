package com.example.springsecurtydemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//创建自定义登录校验类 认证
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //模拟admin为当前登录名,用户不存在或不匹配时抛异常
        if(!"admin".equals(username)){
             throw new UsernameNotFoundException("用户名不存在");
        }
        String password =passwordEncoder.encode("123");
        //"admin,normal":模拟为当前用户添加权限
//      "admin,normal,ROLE_abc"强制要求角色ROLE_开头
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc,ROLE_admin,/insert,/update,/main.html"));
    }
}
