package com.absys.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class UserEntity {

    private String id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String earthCountry;
    private String earthJob;
    private UserStateEnum state = UserStateEnum.CREATED;

    public UserEntity(String id, String firstName, String lastName, Date birthDay, String earthCountry, String earthJob) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.earthCountry = earthCountry;
        this.earthJob = earthJob;
        this.state = UserStateEnum.DONE;
    }
}
