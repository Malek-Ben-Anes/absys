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

        final Map<String, Map<String, List<UserEntity>>> usersByCountryAndCity = groupByCountryAndCity(userEntities);
        JobAndCountryUserGroupDto result = new JobAndCountryUserGroupDto();
        result.setJobs(UserMapper.INSTANCE.groupedUsers(usersByCountryAndCity));
        return result;
    }

    default Map<String, Map<String, List<UserEntity>>> groupByCountryAndCity(List<UserEntity> userEntities) {
        Map<String, Map<String, List<UserEntity>>> result = userEntities.stream().collect(
                groupingBy(UserEntity::getEarthJob,
                        groupingBy(UserEntity::getEarthCountry)
                )
        );
        return CollectionsHelper.convertToTreeMap(result);
    }

    Map<String, Map<String, List<UserDetailsDto>>> groupedUsers(Map<String, Map<String, List<UserEntity>>> userEntities);

    Map<String, List<UserDetailsDto>> groupedByCountryUsers(Map<String, List<UserEntity>> userEntities);

    List<UserDetailsDto> toUserDtos(List<UserEntity> userEntities);
}
