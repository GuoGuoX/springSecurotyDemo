package com.oauth2.springsecurityoauth2demo.config;

import com.oauth2.springsecurityoauth2demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableAuthorizationServer //Authorization授权
public class AuthorizationServiceConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    public AuthenticationManager authenticationManager;
    @Autowired
    public UserService userService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("tokenStore")
    public TokenStore tokenStore;
    @Autowired
    @Qualifier("getJwtAccessTokenConverter")
    public JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    public JwtEnhancer jwtEnhancer;
    //密码模式
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> list = new ArrayList<>();
        list.add(jwtEnhancer);
        list.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(list);
        endpoints.authenticationManager(authenticationManager)
        .userDetailsService(userService)
        //使用JWT认证 整合JWT
        .tokenStore(tokenStore)
        .accessTokenConverter(jwtAccessTokenConverter)
        .tokenEnhancer(tokenEnhancerChain);
        ;
    }
    //授权码模式
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        System.out.println(passwordEncoder);//http://localhost:8080/oauth/authorize?response_type=code&client_id=client&redirect_uri=http://www.baidu.com&scope=all
        clients.inMemory()//放内存
                .withClient("client")//客户端id
                .secret(passwordEncoder.encode("112233"))//密钥
                .redirectUris("http://localhost:8081/login")//重定向地址 认证通过重定到客户端
                .scopes("all")//授权范围

                //设置token过期时间
                .accessTokenValiditySeconds(60)//"error": "invalid_token",
                .refreshTokenValiditySeconds(86400)
                //自动授权配置
                .autoApprove(true)

//                                              "error_description": "Access token expired:
//               //授权类型 有四种：authorization_code
                //password密码模式
                .authorizedGrantTypes("authorization_code","password","refresh_token")

        ;
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        // 获取密钥需要身份认证，使用单点登录时必须配置
        security.tokenKeyAccess("isAuthenticated()");
    }

}
