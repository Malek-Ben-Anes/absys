package com.absys.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class CreateUserRequest {

    @NotEmpty(message = "First name may not be empty")
    private String firstName;

    @NotEmpty(message = "Last name may not be empty")
    private String lastName;

    @NotEmpty(message = "Birth date may not be empty")
    private Date birthDate;

    @NotEmpty(message = "Earth country may not be empty")
    private String earthCountry;

    @NotEmpty(message = "Earth job may not be empty")
    private String earthJob;
}
