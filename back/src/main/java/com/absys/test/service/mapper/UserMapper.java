package com.absys.test.service.mapper;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.JobAndCountryUserGroupDto.UserDetailsDto;
import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.model.UserEntity;
import com.absys.test.util.CollectionsHelper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(UserEntity userEntity);

    List<UserDto> toDtos(List<UserEntity> userEntities);

    UserEntity toEntity(CreateUserRequest createUserRequest);

    default JobAndCountryUserGroupDto toGroupedUserDtos(List<UserEntity> userEntities) {
        if (userEntities == null || userEntities.isEmpty()) return new JobAndCountryUserGroupDto();

        JobAndCountryUserGroupDto result = new JobAndCountryUserGroupDto();

        final Map<String, Map<String, List<UserDetailsDto>>> usersByJobAndCounty = UserMapper.INSTANCE.groupedUsersByJob(groupByCountryAndCity(userEntities));
        result.setJobs(CollectionsHelper.convertToTreeMap(usersByJobAndCounty));
        return result;
    }

    default Map<String, Map<String, List<UserEntity>>> groupByCountryAndCity(List<UserEntity> userEntities) {
        return userEntities.stream().collect(
                groupingBy(UserEntity::getEarthJob,
                        groupingBy(UserEntity::getEarthCountry)
                )
        );
    }

    Map<String, Map<String, List<UserDetailsDto>>> groupedUsersByJob(Map<String, Map<String, List<UserEntity>>> userEntities);

    Map<String, List<UserDetailsDto>> groupedUsersByCountry(Map<String, List<UserEntity>> userEntities);

    List<UserDetailsDto> toUserDetailDtos(List<UserEntity> userEntities);
}
