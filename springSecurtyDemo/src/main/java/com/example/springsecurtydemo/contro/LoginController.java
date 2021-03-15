package com.example.springsecurtydemo.contro;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping ("/login")
    public String login(){
        System.out.println("login执行");
        return "redirect:login.html";
    }

    @RequestMapping ("/test")
    @ResponseBody
    public String test(){
        System.out.println("test执行");
        return "xxx";
    }

    //@Secured使用注解验证是否具有对应角色权限访问（区分大小写ROLE_开头） springboot启动类：@EnableGlobalMethodSecurity(securedEnabled = true)//开启权限注解
//    @Secured("ROLE_abc")
    @PreAuthorize("hasRole('ROLE_abc')")//执行方法前调用，允许ROLE_开头 springboot启动类：@EnableGlobalMethodSecurity(prePostEnabled = true)//开启权限注解
    @RequestMapping ("/toMain")
    public String toMain(){
        System.out.println("toMain执行");
        return "redirect:main.html";
    }
    @RequestMapping ("/failure")
    public String failure(){
        return "redirect:error.html";
    }


    @RequestMapping ("/regex")
    @ResponseBody
    public String regex(){
        return "通过限制请求方式访问成功";
    }

    @RequestMapping ("/403")
    @ResponseBody
    public String response403(){
        return "权限不足";
    }


    @RequestMapping ("/securityTag")
    public String securityTag(){
        return "/securityDemo.html";
    }

}
