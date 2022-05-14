package com.webfinances.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Map<String, String> registerUser(UserRegisterForm userRegisterForm) {
        return Collections.singletonMap("response", "User registered successfully. ✔️");
    }
}
