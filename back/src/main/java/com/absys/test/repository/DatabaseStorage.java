package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

import static com.absys.test.model.UserStatusEnum.CREATED;
import static com.absys.test.model.UserStatusEnum.DONE;

@Component
public class DatabaseStorage {

    /**
     * Common birthDate for all users in order to facilitate the tests
     */
    private static final Date birthDate = Date.from(LocalDate.of(1986, Month.JULY, 01).atStartOfDay(ZoneId.systemDefault()).toInstant());

    /**
     * internal users structure is a key-value structure.
     * DataBaseStorage is the only member that can manage and manipulate this structure with its data.
     * Thus, it achieves the single responsibility personal and encapsulation naturally.
     * <p>
     * Each user has a unique identifier.
     * Key:   ID
     * Value: userEntity
     * This structure keeps the order of insertion each time we add a new user(LinkedHashMap makes that possible).
     */
    private static final Map<String, UserEntity> internalUserEntities = new LinkedHashMap<>();

    private static final List<CriminalEntity> internalEarthCriminalsList = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", birthDate),
            new CriminalEntity("SIMON", "KILLER", birthDate),
            new CriminalEntity("ARNAUD", "DURANT", birthDate),
            new CriminalEntity("THEODORE", "BAGWELL", birthDate)
    );

    static {
        internalUserEntities.put("JHXZ677", new UserEntity("JHXZ677", "MALIK", "BEN ANES", birthDate, "TEXAS", "CRAFTER", DONE));
        internalUserEntities.put("AJM1KSY", new UserEntity("AJM1KSY", "JEAN", "DUPONT", birthDate, "FRANCE", "FARMER", CREATED));
        internalUserEntities.put("8JMAY4G", new UserEntity("8JMAY4G", "JOHN", "DOE", birthDate, "FRANCE", "FARMER", DONE));
        internalUserEntities.put("8JMAY66", new UserEntity("8JMAY66", "THEODORE", "BAGWELL", birthDate, "USA", "HAND-CRAFTER", CREATED));
    }

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(new ArrayList<>(internalUserEntities.values()));
    }

    public List<CriminalEntity> getEarthCriminals() {
        return Collections.unmodifiableList(internalEarthCriminalsList);
    }

    public UserEntity add(final UserEntity userEntity) {
        internalUserEntities.put(userEntity.getId(), userEntity);
        return userEntity;
    }

    public Optional<UserEntity> findById(String userId) {
        return Optional.ofNullable(internalUserEntities.get(userId));
    }

}
