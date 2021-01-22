package com.absys.test.service;

import com.absys.test.model.UserEntity;
import com.absys.test.repository.UserRepository;
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
     * @param userEntity
     * @return
     */
    public UserEntity createUser(UserEntity userEntity) {
        userRepository.add(userEntity);
        try {
            // notify
            webSocketTemplate.convertAndSend("/workflow/states", userEntity);
            return userEntity;
        } catch (Exception e) {
            throw new RuntimeException("Error has occured");
        }

    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param userid
     * @return
     */
    public UserEntity workflow(String userid) {
        UserEntity userEntity = new UserEntity();
        // fetch user from memory database

        // next step on workflow
        // CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
        // Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to REFUSED
        // TODO
        // don't forget to use earthCriminalDatabase and UserState

        // send update to all users
        webSocketTemplate.convertAndSend("/workflow/states", userEntity);
        return userEntity;
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

    /**
     * Find the user in the memory database by its ID
     *
     * @param userid
     * @return
     */
    public UserEntity login(String userid) {
        // TODO
        return null;
    }
}
