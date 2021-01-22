package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.exception.NotAuthenticatedException;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    /**
     * Find the user in the memory database by its ID
     *
     * @param userId
     * @return
     */
    public UserDto login(UUID userId) {
        return userRepository.findById(userId).map(UserMapper.INSTANCE::toDTO)
                .orElseThrow(() -> new NotAuthenticatedException("user not found id: " + userId));
    }
}
