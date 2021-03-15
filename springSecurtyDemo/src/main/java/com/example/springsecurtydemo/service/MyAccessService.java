package com.example.springsecurtydemo.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyAccessService {

    public boolean hasPermission(HttpServletRequest request , Authentication authentication);
}
