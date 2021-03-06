package com.absys.test.repository;

import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final DatabaseStorage storage;

    /**
     * Find user by his registration ID
     *
     * @param userId
     * @return
     */
    public Optional<UserEntity> findById(String userId) {
        return storage.findById(userId);
    }

    /**
     * Save user to the database.
     *
     * @param userEntity
     * @return
     */
    public UserEntity save(UserEntity userEntity) {
        return storage.add(userEntity);
    }

    /**
     * Returns all user group by its job then its country
     *
     * @return
     */
    public List<UserEntity> findAll() {
        return storage.getUsers();
    }
}
