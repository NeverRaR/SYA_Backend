package com.sya.service;

import com.sya.dao.UserDAO;
import com.sya.model.User;
import com.sya.util.HashHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Service
public class AuthenticationService {
    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private UserDAO userDAO;

    public User getUser(String sessionId) {

        if(sessionId == null) return null;
        String id=template.opsForValue().get(sessionId);
        if(id == null) {
            return null;
        }
        Optional<User> optionalUser= userDAO.findById(Integer.parseInt(id));

        if(!optionalUser.isPresent()) return null;
        return  optionalUser.get();
    }

    public String createSessionId(String username,String password){
        User user = userDAO.findByUsername(username);
        if(user == null){
            return null;
        }
        String passwordHashed= HashHelper.computeSha256Hash(password+user.getSalt());
        if(!user.getPassword().equals(passwordHashed)){
            return null;
        }

        double seed= ThreadLocalRandom.current().nextDouble();
        String sessionId=HashHelper.computeMD5Hash(user.getUsername()+ seed);
        template.opsForValue().set(sessionId,user.getId().toString(),72, TimeUnit.HOURS);

        return sessionId;

    }

    public void invalidateSessionId(String sessionId){
        template.delete(sessionId);
    }
}
