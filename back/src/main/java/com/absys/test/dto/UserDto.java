package com.absys.test.dto;

import com.absys.test.model.UserStateEnum;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String earthCountry;
    private String earthJob;
    private UserStateEnum state;
}

