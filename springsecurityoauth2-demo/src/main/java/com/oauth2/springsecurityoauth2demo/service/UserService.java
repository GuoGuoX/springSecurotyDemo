package com.oauth2.springsecurityoauth2demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    public PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(passwordEncoder);
        System.out.println("loadUserByUsername=username"+username);
        String password =passwordEncoder.encode("123456");
        System.out.println("loadUserByUsername=password"+password);
        return new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }


}
