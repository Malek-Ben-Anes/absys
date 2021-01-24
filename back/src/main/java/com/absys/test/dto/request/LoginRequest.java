package com.absys.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class CreateUserRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private Date birthDay;

    @NotBlank
    private String earthCountry;

    @NotBlank
    private String earthJob;
}
