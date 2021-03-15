package com.example.springsecurtydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)//开启权限注解
public class SpringsecurtydemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurtydemoApplication.class, args);
    }

}
