package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
public class DatabaseStorage {

    private final List<UserEntity> internalUsersList = new LinkedList() {{
        add(
                new UserEntity("JHXZ677", "MALIK", "BEN ANES", new Date(), "TEXAS", "CRAFTER"));
        add(
                new UserEntity("AJM1KSY", "DUPONT", "JEAN", new Date(), "FRANCE", "FARMER"));
        add(
                new UserEntity("8JMAY4G", "JOHN", "DOE", new Date(), "FRANCE", "FARMER"));
    }};

    private List<CriminalEntity> internalEarthCriminalsList = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", false),
            new CriminalEntity("SIMON", "DUPONT", true),
            new CriminalEntity("ARNAUD", "DURANT", true)
    );

    public List<UserEntity> getUsers() {
        return Collections.unmodifiableList(internalUsersList);
    }

    public List<CriminalEntity> getEarthCriminals() {
        return Collections.unmodifiableList(internalEarthCriminalsList);
    }

    public UserEntity add(UserEntity userEntity) {
        internalUsersList.add(userEntity);
        return userEntity;
    }

    public CriminalEntity add(CriminalEntity criminalEntity) {
        internalEarthCriminalsList.add(criminalEntity);
        return criminalEntity;
    }
}
