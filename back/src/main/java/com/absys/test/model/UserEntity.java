package com.absys.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEntity {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String earthCountry;
    private String earthJob;
    private UserStateEnum state = UserStateEnum.CREATED;
}
