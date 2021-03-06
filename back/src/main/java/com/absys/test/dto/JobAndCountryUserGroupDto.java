package com.absys.test.dto;

import com.absys.test.model.UserStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class JobAndCountryUserGroupDto {

    Map<String, Map<String, List<UserDetailsDto>>> jobs = new TreeMap<>();

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDetailsDto {
        private String id;
        private String firstName;
        private String lastName;
        @JsonFormat(pattern="yyyy-MM-dd")
        private Date birthDate;
        private UserStatusEnum status;
    }
}

