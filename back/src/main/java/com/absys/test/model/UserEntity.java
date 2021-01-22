package com.absys.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Data
public class UserEntity {

    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthDay;
    private String earthCountry;
    private String earthJob;
    private UserStateEnum state = UserStateEnum.CREATED;

    public UserEntity(String id, String firstName, String lastName, Date birthDay, String earthCountry, String earthJob) {
        this.id = UUID.fromString(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.earthCountry = earthCountry;
        this.state = UserStateEnum.DONE;
        this.earthJob = earthJob;
    }
}
