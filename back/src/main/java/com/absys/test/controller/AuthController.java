package com.absys.test.controller;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.LoginRequest;
import com.absys.test.service.AuthService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Login client with registrationId", nickname = "login", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User has been successfully logged in."),
            @ApiResponse(code = 400, message = "Invalid request: given registrationId might be blank."),
            @ApiResponse(code = 401, message = "User could not log in."),
            @ApiResponse(code = 500, message = "An error occurred server-side.")
    })
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
