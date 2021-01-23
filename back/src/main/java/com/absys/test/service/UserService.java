package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.exception.NotFoundException;
import com.absys.test.model.UserEntity;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private static final int MARS_USER_ID_LENGTH = 7;

    private final UserRepository userRepository;
    private final SimpMessagingTemplate webSocketTemplate;

    /**
     * Create an ID and a user then return the ID
     *
     * @param createRequest
     * @return
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

    public List<UserDto> findAll() {
        return UserMapper.INSTANCE.toDtos(userRepository.findAll());
    }

    /**
     * @param userId
     * @return 1- Fetch user from memory database
     * next step on workflow
     * CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
     * Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
     * TODO
     * don't forget to use earthCriminalDatabase and UserState
     * <p>
     * send update to all users
     */
    public UserDto workflow(String userId) {
        // fetch user from memory database
        UserDto userDto = userRepository.findById(userId).map(UserMapper.INSTANCE::toDto)
                .orElseThrow(() -> new NotFoundException("User not found id: " + userId));

        // next step on workflow
        // CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
        // Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
        // TODO
        // don't forget to use earthCriminalDatabase and UserState

        // send update to all users
        webSocketTemplate.convertAndSend("/workflow/states", userDto);
        return userDto;
    }


    /**
     * Return all user group by its job then its country
     *
     * @return
     */
    public Object findByJobThenCountry() {
        // TODO : Return an Object containing user sort by Job then Country (you are not allowed to just return List<User> sorted)
        return new ArrayList<>(0);
    }

}
