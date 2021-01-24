package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.absys.test.model.UserStateEnum.DONE;

@Component
public class DatabaseStorage {

    private final Map<String, UserEntity> internalUsersList = new HashMap<String, UserEntity>() {{
        put("JHXZ677", new UserEntity("JHXZ677", "MALIK", "BEN ANES", new Date(), "TEXAS", "CRAFTER", DONE));
        put("AJM1KSY", new UserEntity("AJM1KSY", "JEAN", "DUPONT", new Date(), "FRANCE", "FARMER", DONE));
        put("8JMAY4G", new UserEntity("8JMAY4G", "JOHN", "DOE", new Date(), "FRANCE", "FARMER", DONE));
        put("8JMAY66", new UserEntity("8JMAY66", "THEODORE", "BAGWELL", new Date(), "USA", "HAND-CRAFTER", DONE));
    }};

    private final List<CriminalEntity> internalEarthCriminalsList = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", false),
            new CriminalEntity("SIMON", "DUPONT", true),
            new CriminalEntity("ARNAUD", "DURANT", true),
            new CriminalEntity("THEODORE", "BAGWELL", true)
    );

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(internalUsersList.values()));
    }

    public List<CriminalEntity> getEarthCriminals() {
        return Collections.unmodifiableList(internalEarthCriminalsList);
    }

    public UserEntity add(UserEntity userEntity) {
        internalUsersList.put(userEntity.getId(), userEntity);
        return userEntity;
    }

    public Optional<UserEntity> findById(String userId) {
        return Optional.ofNullable(internalUsersList.get(userId));
    }

}
