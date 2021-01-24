package com.absys.test.dto;

import com.absys.test.model.UserStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class JobAndCountryUserGroupDto {

    Map<String, Map<String, List<UserDetailsDto>>> users = new TreeMap<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailsDto {
        private String id;
        private String firstName;
        private String lastName;
        private Date birthDay;
        private UserStateEnum state;
    }
}

