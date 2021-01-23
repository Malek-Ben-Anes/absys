package com.absys.test.controller;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.model.UserEntity;
import com.absys.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        // TODO paginate the response
        return ResponseEntity.ok(userService.findAll());
    }

    // verify patch or put
    @PatchMapping("/workflow/{userId}")
    public ResponseEntity<UserDto> workflow(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.workflow(userId));
    }

    @GetMapping("/byJobThenCountry")
    public Object findByJobThenCountry() {
        return userService.findByJobThenCountry();
    }

}
