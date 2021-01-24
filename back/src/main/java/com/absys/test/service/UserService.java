package com.absys.test.service;

import com.absys.test.dto.JobAndCountryUserGroupDto;
import com.absys.test.dto.UserDto;
import com.absys.test.dto.request.CreateUserRequest;
import com.absys.test.exception.NotFoundException;
import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import com.absys.test.model.UserStatusEnum;
import com.absys.test.repository.CriminalRepository;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private static final int MARS_USER_ID_LENGTH = 7;

    private final UserRepository userRepository;
    private final CriminalRepository criminalRepository;
    private final SimpMessagingTemplate webSocketTemplate;

    /**
     * Create a new user from @createRequest.
     * A registration ID will be generated automatically following norme MARS-51*2
     *
     * @param createRequest
     * @return UserDto
     */
    public UserDto createUser(CreateUserRequest createRequest) {
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(createRequest);
        String generatedUuid = RandomStringUtils.randomAlphanumeric(MARS_USER_ID_LENGTH).toUpperCase();
        userEntity.setId(generatedUuid);

        try {
            userEntity = userRepository.save(userEntity);
            UserDto userDto = UserMapper.INSTANCE.toDto(userEntity);

            // Notify subscriber with the newly created user.
            webSocketTemplate.convertAndSend("/workflow/states", userDto);
            log.info("New user has been created with id: {}", userDto.getId());
            return userDto;
        } catch (Exception e) {
            log.error("Error has occurred during new user creation!");
            throw new RuntimeException("Error has occurred during new user creation!");
        }
    }

    /**
     * Returns list of users that exists in the database.
     *
     * @return List<UserDto>
     */
    public List<UserDto> findAll() {
        return UserMapper.INSTANCE.toDtos(userRepository.findAll());
    }

    /**
     * TODO: We can apply Chain of responsibility Design Pattern in this context. And that in case the application will evolve in the future.
     * TODO: We can even integrate one of workflow frameworks such as: Alfresco Activiti, Bonitasoft jBPM...
     *
     *
     * In fact, we can divide each step of the workflow into a single class named for example "EarthProcessor"
     * and another class named "MarsProcessor" than we chain all of them, in order to achieve our workflow.
     * <p>
     * Registration workFlow consists of checking user state each time and launch a controlling process
     * CREATED -> EARTH_CONTROL -> MARS_CONTROL -> DONE
     * <p>
     * 1- fetch user from user database
     * 2- Check criminal list during "EARTH_CONTROL" state, if the user is in the list, set state to "REFUSED"
     * 3- Achieve "MARS_CONTROL" process
     * 4- Control process is completed
     * <p>
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
        userEntity.setStatus(UserStatusEnum.EARTH_CONTROL);
        Optional<CriminalEntity> criminalOptional = criminalRepository.findCriminalByCriteria(userEntity.getFirstName(), userEntity.getLastName(), userEntity.getBirthDate());
        if (criminalOptional.isPresent()) {
            userEntity.setStatus(UserStatusEnum.REFUSED);
            log.info("Mars registration request has been rejected for userId: {} during Earth Control " + userEntity.getId());
        }

        // 3- Achieve Mars Control process
        if (!UserStatusEnum.REFUSED.equals(userEntity.getStatus())) {
            userEntity.setStatus(UserStatusEnum.MARS_CONTROL);
        }

        // 4- Control process is completed,
        if (!UserStatusEnum.REFUSED.equals(userEntity.getStatus())) {
            userEntity.setStatus(UserStatusEnum.DONE);
        }

        // save userEntity in order to persist its last state
        userEntity = userRepository.save(userEntity);
        UserDto userDto = UserMapper.INSTANCE.toDto(userEntity);

        // Notify subscriber with the newly updated user.
        webSocketTemplate.convertAndSend("/workflow/states", userDto);
        log.info("WorkFlow for userId: {} - current status: {}", userDto.getId(), userDto.getStatus());
        return userDto;
    }

    /**
     * Return an Object containing user sort by Job then Country (you are not allowed to just return List<User> sorted)
     *
     * @return JobAndCountryUserGroupDto
     */
    public JobAndCountryUserGroupDto findUsersGroupedByJobThenCountry() {
        List<UserEntity> userEntities = userRepository.findAll();
        return UserMapper.INSTANCE.toGroupedUserDtos(userEntities);
    }

}
