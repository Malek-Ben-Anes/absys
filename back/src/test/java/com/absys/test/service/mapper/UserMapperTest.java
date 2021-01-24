package com.absys.test.service.mapper;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.JobAndCountryUserGroupDto.UserDetailsDto;
import com.absys.test.model.UserEntity;
import com.absys.test.model.UserStateEnum;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    @Test
    void sanitize_toGroupedUserDtos() {
        assertNotNull(UserMapper.INSTANCE.toGroupedUserDtos(null));
        assertNotNull(UserMapper.INSTANCE.toGroupedUserDtos(new ArrayList<>()));
    }

    @Test
    void toGroupedUserDtos() {
        final List<UserEntity> userEntities = new ArrayList<>();
        final Date birthDate = new Date();
        userEntities.add(new UserEntity("JHXZ677", "MALIK", "BEN ANES", birthDate, "TEXAS", "CRAFTER"));
        userEntities.add(new UserEntity("JHXZ6CC", "JOE", "BIDEN", birthDate, "FRANCE", "CRAFTER"));
        userEntities.add(new UserEntity("JHXZ6DD", "ELON", "MASK", birthDate, "TEXAS", "CRAFTER"));
        userEntities.add(new UserEntity("AJM1KSY", "DUPONT", "JEAN", birthDate, "FRANCE", "FARMER"));
        userEntities.add(new UserEntity("8JMAY4G", "JOHN", "DOE", birthDate, "FRANCE", "FARMER"));
        userEntities.add(new UserEntity("JHXZ6FF", "CHARLIE", "CHAPLIN", birthDate, "SWISS", "COMEDIAN"));


        Map<String, Map<String, List<UserDetailsDto>>> result = new TreeMap<>();

        // 1- crafters
        List<UserDetailsDto> craftersTexas = new ArrayList<>();
        craftersTexas.add(new UserDetailsDto("JHXZ677", "MALIK", "BEN ANES", birthDate, UserStateEnum.DONE));
        craftersTexas.add(new UserDetailsDto("JHXZ6DD", "ELON", "MASK", birthDate, UserStateEnum.DONE));
        Map<String, List<UserDetailsDto>> craftersTexasMap = groupByCountry( "TEXAS", craftersTexas);

        List<UserDetailsDto> craftersFrance = new ArrayList<>();
        craftersFrance.add(new UserDetailsDto("JHXZ6CC", "JOE", "BIDEN", birthDate, UserStateEnum.DONE));

        Map<String, List<UserDetailsDto>> craftersFranceMap = groupByCountry("FRANCE", craftersFrance);
        craftersTexasMap.putAll(craftersFranceMap);
        result.put("CRAFTER", craftersTexasMap);

        // 2- farmers
        List<UserDetailsDto> farmers = new ArrayList<>();
        farmers.add(new UserDetailsDto("AJM1KSY", "DUPONT", "JEAN", birthDate, UserStateEnum.DONE));
        farmers.add(new UserDetailsDto("8JMAY4G", "JOHN", "DOE", birthDate, UserStateEnum.DONE));
        result.put("FARMER", groupByCountry( "FRANCE", farmers));

        // 3- comedian
        List<UserDetailsDto> comedian = new ArrayList<>();
        comedian.add(new UserDetailsDto("JHXZ6FF", "CHARLIE", "CHAPLIN", birthDate, UserStateEnum.DONE));
        result.put("COMEDIAN", groupByCountry( "SWISS", comedian));

        JobAndCountryUserGroupDto expected = new JobAndCountryUserGroupDto();
        expected.setUsers(result);

        assertEquals(expected, UserMapper.INSTANCE.toGroupedUserDtos(userEntities));
    }

    private Map<String, List<UserDetailsDto>> groupByCountry(String countryName, List<UserDetailsDto> users) {
        Map<String, List<UserDetailsDto>> countryGroup = new TreeMap<>();
        countryGroup.put(countryName, users);
        return countryGroup;
    }
}