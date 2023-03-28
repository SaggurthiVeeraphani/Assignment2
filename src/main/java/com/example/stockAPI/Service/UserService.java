package com.example.stockAPI.Service;

import com.example.stockAPI.Entity.User;
import com.example.stockAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;

    // Method to save user to database
    public void save(User user) {

        userRepository.save(user);
    }

    // Method to validate user login credentials
    public boolean login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // Method to generate session ID
    public String generateSessionId(String email) {
        String sessionId = UUID.randomUUID().toString();
        User user = userRepository.findByEmail(email);
        user.setSessionId(sessionId);
        userRepository.save(user);
        return sessionId;
    }
    /*private String setSessionId(String sessionId){
        return new String(Base64.getDecoder().decode(sessionId.split(";")[0].split("=")[1]));
    }*/
}
