package com.absys.test.repository;

import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserRepository {

    private final DatabaseStorage storage;

    /**
     * Create an ID and a user then return the ID
     *
     * @param userId
     * @return
     */
    public Optional<UserEntity> findById(String userId) {
        return storage.getUsers().stream()
                .filter(user -> userId.equals(user.getId()))
                .findFirst();
    }

    /**
     * Create an ID and a user then return the ID
     *
     * @param userEntity
     * @return
     */
    public UserEntity save(UserEntity userEntity) {
        storage.getUsers().add(userEntity);
        return userEntity;
    }

    /**
     * Return all user group by its job then its country
     *
     * @return
     */
    public List<UserEntity> findAll() {
        return storage.getUsers();
    }
}
