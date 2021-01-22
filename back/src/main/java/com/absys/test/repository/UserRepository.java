package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserRepository {

    private List<UserEntity> memoryDatabase = new LinkedList(){{add(
            new UserEntity("SFES45", "MALIK", "BEN ANES", new Date(), "TEXAS", "CRAFTER"));
        add(
                new UserEntity("SFES46", "DUPONT", "JEAN", new Date(), "FRANCE", "FARMER"));
        add(
                new UserEntity("SFES47", "JOHN", "DOE", new Date(), "FRANCE", "FARMER"));}};

    private List<CriminalEntity> earthCriminalEntityDatabase = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", false),
            new CriminalEntity("SIMON", "DUPONT", true),
            new CriminalEntity("ARNAUD", "DURANT", true)
    );

    /**
     * Create an ID and a user then return the ID
     * @param userEntity
     * @return
     */
    public UserEntity add(UserEntity userEntity) {
        String key = "TODO : generate random string here";
        userEntity.setId(key);
        memoryDatabase.add(userEntity);
        return userEntity;
    }

    /**
     * Return all user group by its job then its country
     * @return
     */
    public List<UserEntity> findAll() {
        return memoryDatabase;
    }
}
