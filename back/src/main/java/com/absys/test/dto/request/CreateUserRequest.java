package com.absys.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateUserRequest {

    @NotEmpty(message = "First name may not be empty")
    private String firstName;

    @NotEmpty(message = "Last name may not be empty")
    private String lastName;

    @NotNull(message = "Birth date may not be empty")
    private Date birthDate;

    @NotEmpty(message = "Earth country may not be empty")
    private String earthCountry;

    @NotEmpty(message = "Earth job may not be empty")
    private String earthJob;
}
