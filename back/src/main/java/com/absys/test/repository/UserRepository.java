package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserRepository {

    private List<UserEntity> usersDatabase = new LinkedList(){{add(
            new UserEntity("e588c24f-1f6e-405e-8c4b-bbde5ae60706", "MALIK", "BEN ANES", new Date(), "TEXAS", "CRAFTER"));
        add(
                new UserEntity("80976f51-2491-44e4-bdcd-4f73e807e072", "DUPONT", "JEAN", new Date(), "FRANCE", "FARMER"));
        add(
                new UserEntity("06f96f09-f372-489c-ac51-6bd73758fc7f", "JOHN", "DOE", new Date(), "FRANCE", "FARMER"));}};

    /**
     * Create an ID and a user then return the ID
     * @param userId
     * @return
     */
    public Optional<UserEntity> findById(UUID userId) {
        return usersDatabase.stream()
                .filter(user -> userId.equals(user.getId()))
                .findFirst();
    }

    /**
     * Create an ID and a user then return the ID
     * @param userEntity
     * @return
     */
    public UserEntity add(UserEntity userEntity) {
        UUID generatedUuid = UUID.randomUUID();
        userEntity.setId(generatedUuid);
        usersDatabase.add(userEntity);
        return userEntity;
    }

    /**
     * Return all user group by its job then its country
     * @return
     */
    public List<UserEntity> findAll() {
        return usersDatabase;
    }
}
