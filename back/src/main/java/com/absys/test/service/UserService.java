package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.model.UserEntity;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

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
        try {
            userEntity = userRepository.add(userEntity);
            // notify
            webSocketTemplate.convertAndSend("/workflow/states", userEntity);
            return UserMapper.INSTANCE.toDTO(userEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error has occured");
        }

    }

    public List<UserDto> findAll() {
        return UserMapper.INSTANCE.toDTOs(userRepository.findAll());
    }

    /**
     * @param userid
     * @return
     *
     * fetch user from memory database
     * next step on workflow
     * CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
     * Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
     * TODO
     * don't forget to use earthCriminalDatabase and UserState
     *
     * send update to all users
     */
    public UserDto workflow(String userid) {
        UserEntity userEntity = new UserEntity();
        // fetch user from memory database

        // next step on workflow
        // CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
        // Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
        // TODO
        // don't forget to use earthCriminalDatabase and UserState

        // send update to all users
        webSocketTemplate.convertAndSend("/workflow/states", userEntity);
        return UserMapper.INSTANCE.toDTO(userEntity);
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
