package com.absys.test.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String earthCountry;
    private String earthJob;
}
