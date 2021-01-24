package com.absys.test.controller;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody CreateUserRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        // TODO paginate the response
        return ResponseEntity.ok(userService.findAll());
    }

    @PatchMapping("/{userId}/workflow")
    public ResponseEntity<UserDto> workflow(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.workflow(userId));
    }

    @GetMapping("/earthJob/country")
    public ResponseEntity<JobAndCountryUserGroupDto> findByJobThenCountry() {
        return ResponseEntity.ok(userService.findUsersGroupedByJobThenCountry());
    }

}
