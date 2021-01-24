package com.absys.test.dto;

import com.absys.test.model.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String id;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    private String earthCountry;

    private String earthJob;

    private UserStatusEnum status;
}

