package com.example.springsecurtydemo.config;

import com.example.springsecurtydemo.handler.MyAccessDeniedHandler;
import com.example.springsecurtydemo.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurtyConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public MyUserDetailService userDetailsService ;
    @Autowired
    public DataSource dataSource;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("SecurtyConfig.configure方法执行");
        //授权
        http.authorizeRequests()
//                permitAll():允许所有请求
                //放行当前资源 注意:此配置必须放在.anyRequest()authenticated()前面 否则报错Can't configure antMatchers after anyRequest
                .antMatchers("/login.html").permitAll()

                //放行登录失败拦截
//                .antMatchers("/error.html").permitAll()
                //放行静态资源
//                .antMatchers("/css/**","/js/**","/image/**").permitAll()
//                放行后缀
//                .antMatchers("/**/*.png").permitAll()
//                .regexMatchers(".+[.]jpg").permitAll()
                //放行指定请求方式
//                .regexMatchers(HttpMethod.GET,"/regex").permitAll()
                //加前缀,作用全局   与@RequestMapping("/xxx")不同, (要在配置文件加spring.mvc.servlet.path=/xxx)
//                .mvcMatchers("/regex").servletPath("/xxx").permitAll()
                //基于权限控制
//                .antMatchers("/regex").hasAnyAuthority("admin ")
                //可配置多个权限,满足其中一个也可访问
//                .antMatchers("/regex").hasAnyAuthority("admin ","ADMINN")
                //所有请求必须登录才能访问 (必须和UserDetails中配置的去掉前缀后的角色名一致)
                //new User(username,password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc"));
//                .antMatchers("/regex").hasAnyRole("abc")
//                限制基于id访问(只能在hasIpAddress方法中配置的ip一致才能访问)
//                .antMatchers("/regex").hasIpAddress("127.0.0.1")

                .anyRequest().authenticated();//所有请求都需要认证
                //自定义access处理
//                .antMatchers("/main.html").access("hasRole('abc')")//访问/main.html时使用自定义access处理
//                .antMatchers("/main.html").access("@myAccessServiceImpl.hasPermission(request,authentication)");//访问/main.html时使用自定义access处理
//                .anyRequest().access("@myAccessServiceImpl.hasPermission(httpServletRequest,authentication)");//只放行对应已有权限的请求
//                .anyRequest().access("@myAccessServiceImpl.hasPermission(request,authentication)");//只放行对应已有权限的请求

        http.formLogin()
//              用户名密码必须是username和password 除非自定义配置
                .usernameParameter("username123")
                .passwordParameter("password123")
                //自定义登录页面,开启这行代码,将不会进入Security自带的登录页面
                .loginPage("/login.html")
                //必须和表单提交路径一致,表单提交会执行自定义登录逻辑
                .loginProcessingUrl("/login")
                //登录成功跳转页面 这里只支持POST提交Request method 'POST' not supported
//                .successForwardUrl("/main.html");//这种直接请求的方式不支持get
                .successForwardUrl("/toMain")//用的重定向post
//              前后端分离successHandler和failureHandler用的多
                //前后端分离 执行页面跳转
//                .successHandler(new MySuccessHandler("/main.html"))
                //登录失败页面跳转
//                .failureForwardUrl("/failure")
//                .failureHandler(new MyFailureHandler("/error.html"))

        ;
        //记住我
        http.rememberMe()
//                .rememberMeParameter("remember");//设置前端对应remenberme复选框功能参数名
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)//设置60秒过期
                .userDetailsService(userDetailsService)//使用自定义登录逻辑
        ;
        http.logout()
//                .logoutUrl("/xx/loginout")//配置退出连接
                .logoutSuccessUrl("/login.html");
        //权限不足跳转页面
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler());
        //关闭跨域保护
        http.csrf().disable();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setCreateTableOnStartup(true);//设置首次启动时，自动建表 第二次启动注释掉 否则Table 'persistent_logins' already exists
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
