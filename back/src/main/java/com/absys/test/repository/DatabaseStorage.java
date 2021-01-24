package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.absys.test.model.UserStatusEnum.DONE;

@Component
public class DatabaseStorage {

    /**
     * internal users structure is a key-value structure. Each user has a unique identifier.
     * Key: userEntity ID
     * Value: userEntity
     * This structure keeps the order of insertion each time we add a new user.
     */
    private static final Map<String, UserEntity> internalUserEntities = new LinkedHashMap<>();

    private static final List<CriminalEntity> internalEarthCriminalsList = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", false),
            new CriminalEntity("SIMON", "KILLER", true),
            new CriminalEntity("ARNAUD", "DURANT", true),
            new CriminalEntity("THEODORE", "BAGWELL", true)
    );

    static {
        internalUserEntities.put("JHXZ677", new UserEntity("JHXZ677", "MALIK", "BEN ANES", new Date(), "TEXAS", "CRAFTER", DONE));
        internalUserEntities.put("AJM1KSY", new UserEntity("AJM1KSY", "JEAN", "DUPONT", new Date(), "FRANCE", "FARMER", DONE));
        internalUserEntities.put("8JMAY4G", new UserEntity("8JMAY4G", "JOHN", "DOE", new Date(), "FRANCE", "FARMER", DONE));
        internalUserEntities.put("8JMAY66", new UserEntity("8JMAY66", "THEODORE", "BAGWELL", new Date(), "USA", "HAND-CRAFTER", DONE));
    }

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(internalUserEntities.values()));
    }

    public List<CriminalEntity> getEarthCriminals() {
        return Collections.unmodifiableList(internalEarthCriminalsList);
    }

    public UserEntity add(UserEntity userEntity) {
        internalUserEntities.put(userEntity.getId(), userEntity);
        return userEntity;
    }

    public Optional<UserEntity> findById(String userId) {
        return Optional.ofNullable(internalUserEntities.get(userId));
    }

}
