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
     * Find user by his registration ID
     * @param userId
     * @return
     */
    public Optional<UserEntity> findById(String userId) {
        return storage.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst();
    }

    /**
     * Save user to the database.
     *
     * @param userEntity
     * @return
     */
    public UserEntity save(UserEntity userEntity) {
        storage.add(userEntity);
        return userEntity;
    }

    /**
     * Returns all user group by its job then its country
     * @return
     */
    public List<UserEntity> findAll() {
        return storage.getUsers();
    }
}
