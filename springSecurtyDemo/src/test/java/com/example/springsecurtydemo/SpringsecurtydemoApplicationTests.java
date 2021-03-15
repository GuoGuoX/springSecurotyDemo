package com.example.springsecurtydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SpringsecurtydemoApplicationTests {

    @Test
    void contextLoads() {
        //密码解析器:是对BCrypt强散列方法的具体实现.是基于hash算法的单向加密
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123");
        boolean matches = bCryptPasswordEncoder.matches("123",encode);
        System.out.println("=======================");
        System.out.println(matches);
    }

}
