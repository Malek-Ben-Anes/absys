package com.absys.test.controller;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Create new user", nickname = "registerUser", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User has been successfully created."),
            @ApiResponse(code = 400, message = "Invalid request: one field might be blank."),
            @ApiResponse(code = 500, message = "An error occurred server-side.")
    })
    @PostMapping
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody CreateUserRequest createRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createRequest));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        // TODO It would be better to return a paginated response instead of a complete list.
        return ResponseEntity.ok(userService.findAll());
    }

    @ApiOperation(value = "Create new user", nickname = "workflow", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Registration User Workflow has been successfully executed."),
            @ApiResponse(code = 400, message = "Invalid request: one field might be blank."),
            @ApiResponse(code = 404, message = "User not found."),
            @ApiResponse(code = 500, message = "An error occurred server-side.")
    })
    @PatchMapping("/{userId}/workflow")
    public ResponseEntity<UserDto> workflow(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(userService.workflow(userId));
    }

    @GetMapping("/earthJob/country")
    public ResponseEntity<JobAndCountryUserGroupDto> findByJobThenCountry() {
        return ResponseEntity.ok(userService.findUsersGroupedByJobThenCountry());
    }

}
