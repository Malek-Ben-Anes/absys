package com.absys.test.controller;

import com.absys.test.model.UserEntity;
import com.absys.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }

    @GetMapping
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    @GetMapping("/workflow/{userid}")
    public UserEntity workflow(@PathVariable("userid") String userId) {
        return userService.workflow(userId);
    }

    @GetMapping("/byJobThenCountry")
    public Object findByJobThenCountry() {
        return userService.findByJobThenCountry();
    }

}
