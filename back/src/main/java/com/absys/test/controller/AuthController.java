package com.absys.test.controller;

import com.absys.test.model.UserEntity;
import com.absys.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public UserEntity login(@RequestBody String userId) {
        return userService.login(userId);
    }
}
