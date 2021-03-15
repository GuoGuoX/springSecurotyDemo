package com.oauth2.springsecurityoauth2demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class JwtTokenStoreConfig {

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(getJwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter getJwtAccessTokenConverter(){
        JwtAccessTokenConverter j =   new JwtAccessTokenConverter();
        //设置密钥
        j.setSigningKey("test_key");
        return j;
    }
}
