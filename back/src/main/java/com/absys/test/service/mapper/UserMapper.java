package com.absys.test.service.mapper;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDTO(UserEntity userEntity);

    List<UserDto> toDTOs(List<UserEntity> userEntities);

    UserEntity toEntity(CreateUserRequest createUserRequest);
}
