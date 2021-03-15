package com.example.oauthclientdemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCurrentUser")
    public  Object getCurrentUser(Authentication authentication) {
        return authentication;
        /*
        * {"authorities":[{"authority":"admin"}],"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":"55ADD26B3441093563B9BE907C88D9CA","tokenValue":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbmhhbmNlciI6ImltIGlzIEVuaGFuY2VyIGluZm8iLCJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2MTUzMDQ3OTIsImF1dGhvcml0aWVzIjpbImFkbWluIl0sImp0aSI6IjNiYTA3YjM4LWY1M2EtNGVjMS1hZGRkLTk4Nzc5NGU0Njc0NyIsImNsaWVudF9pZCI6ImNsaWVudCJ9.2dexS57KUfBJQXCu81EH2dREtKGSoIRUggwV3b81owM","tokenType":"bearer","decodedDetails":null},"authenticated":true,"userAuthentication":{"authorities":[{"authority":"admin"}],"details":null,"authenticated":true,"principal":"admin","credentials":"N/A","name":"admin"},"oauth2Request":{"clientId":"client","scope":["all"],"requestParameters":{"client_id":"client"},"resourceIds":[],"authorities":[],"approved":true,"refresh":false,"redirectUri":null,"responseTypes":[],"extensions":{},"grantType":null,"refreshTokenRequest":null},"principal":"admin","credentials":"","clientOnly":false,"name":"admin"}
        * */
    }
    }