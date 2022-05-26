package com.webfinances.user;

import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepo userRepo;
    private final FirebaseAuth firebaseAuth;

    public Map<String, String> registerUser(UserRegisterForm userRegisterForm) {

        return Collections.singletonMap("response", "User registered successfully. ✔️");
    }
}
