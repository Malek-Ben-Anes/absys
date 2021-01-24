package com.absys.test.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty(message = "ConnexionId may not be empty")
    private String connexionId;
}
