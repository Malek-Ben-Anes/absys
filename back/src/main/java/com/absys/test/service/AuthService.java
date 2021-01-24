package com.absys.test.service;

import com.absys.test.dto.UserDto;
import com.absys.test.exception.BadCredentialsException;
import com.absys.test.repository.UserRepository;
import com.absys.test.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;

    /**
     * Check whether user is already registered to the application or not.
     * User only needs his registration Id to login     *
     * @param userId
     * @return
     */
    public UserDto login(String userId) {
        return userRepository.findById(userId).map(UserMapper.INSTANCE::toDto)
                .orElseThrow(() -> new BadCredentialsException("Credentials are not corrected! please try again!"));
    }
}
