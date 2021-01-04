package com.sya.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticationService {
    @Autowired
    private StringRedisTemplate template;

    public String getUserId(HttpServletRequest request) {
        String sessionId=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equals(cookie.getName())) {
                    sessionId=cookie.getValue();
                    break;
                }
            }
        }
        if(sessionId != null){
            return template.opsForValue().get(sessionId);
        }
        return null;
    }
}
