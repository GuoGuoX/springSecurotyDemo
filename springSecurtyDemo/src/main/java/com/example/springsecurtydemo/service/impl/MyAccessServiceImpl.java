package com.example.springsecurtydemo.service.impl;


import com.example.springsecurtydemo.service.MyAccessService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
@Service
public class MyAccessServiceImpl  implements MyAccessService {


    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object credentials = authentication.getPrincipal();
        System.out.println("//uri="+request.getRequestURI());
        System.out.println("//url"+request.getRequestURL());
        if(credentials instanceof UserDetails){
            credentials = (UserDetails)credentials;
            //获取权限
            Collection<? extends GrantedAuthority> authorities = ((UserDetails) credentials).getAuthorities();
            //判断请求url是否有权限
            //到User配置类获取是否有当前请求对应的权限
//           return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc"));
            System.out.println("uri="+request.getRequestURI());
            System.out.println("url"+request.getRequestURL());
           return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));
        }
        return false;
    }
}
