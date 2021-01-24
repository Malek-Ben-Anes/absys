package com.absys.test.service;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.exception.NotFoundException;
import com.absys.test.model.UserEntity;
import com.absys.test.model.UserStateEnum;
import com.absys.test.repository.CriminalRepository;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final int MARS_USER_ID_LENGTH = 7;

    private final UserRepository userRepository;
    private final CriminalRepository criminalRepository;
    private final SimpMessagingTemplate webSocketTemplate;

    /**
     * Create a new user from @createRequest. A registration ID will be generated automatically.
     *
     * @param  createRequest
     * @return UserDto
     */
    public UserDto createUser(CreateUserRequest createRequest) {
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(createRequest);
        String generatedUuid = RandomStringUtils.randomAlphanumeric(MARS_USER_ID_LENGTH).toUpperCase();
        userEntity.setId(generatedUuid);
        try {
            userEntity = userRepository.save(userEntity);
            UserDto userDto = UserMapper.INSTANCE.toDto(userEntity);
            // notify
            webSocketTemplate.convertAndSend("/workflow/states", userDto);
            return userDto;
        } catch (Exception e) {
            throw new RuntimeException("Error has occured");
        }

    }

    /**
     * Returns list of users that exists in the database.
     * @return List<UserDto>
     */
    public List<UserDto> findAll() {
        return UserMapper.INSTANCE.toDtos(userRepository.findAll());
    }

    /**
     *
     * Registration workFlow consists of checking user state each time and launch a controlling process
     * CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
     *
     * 1- fetch user from memory database
     * 2- Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
     * 3- Achieve Mars Control process
     * 4- Control process is completed
     *
     * At last, send update to all users (this is not included in the control process)
     *
     * @param userId
     * @return UserDto
     */
    public UserDto workflow(String userId) {
        // 1- fetch user from memory database
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found id: " + userId));

        // 2- Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
        userEntity.setState(UserStateEnum.EARTH_CONTROL);
        if (criminalRepository.findCriminalByCriteria(userEntity.getFirstName(), userEntity.getLastName()).isPresent()) {
            userEntity.setState(UserStateEnum.REFUSED);
        }

        // 3- Achieve Mars Control process
        if (!UserStateEnum.REFUSED.equals(userEntity.getState())) {
            userEntity.setState(UserStateEnum.MARS_CONTROL);
        }

        // 4- Control process is completed,
        if (!UserStateEnum.REFUSED.equals(userEntity.getState())) {
            userEntity.setState(UserStateEnum.DONE);
        }

        // save userEntity in order to persist its last state
        userEntity = userRepository.save(userEntity);
        UserDto userDto = UserMapper.INSTANCE.toDto(userEntity);
        // send update to all users
        webSocketTemplate.convertAndSend("/workflow/states", userDto);
        return userDto;
    }


    /**
     * Return an Object containing user sort by Job then Country (you are not allowed to just return List<User> sorted)
     * @return JobAndCountryUserGroupDto
     */
    public JobAndCountryUserGroupDto findUsersGroupedByJobThenCountry() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserMapper.INSTANCE.toGroupedUserDtos(userEntities);
    }

}
