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
    private String firstname;
    private String lastname;
    private Date birthday;
    private String earthCountry;
    private String earthJob;
    private UserStateEnum state = UserStateEnum.CREATED;

    public UserEntity(String id, String firstname, String lastname, Date birthday, String earthCountry, String earthJob) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.earthCountry = earthCountry;
        this.state = UserStateEnum.DONE;
        this.earthJob = earthJob;
    }
}
