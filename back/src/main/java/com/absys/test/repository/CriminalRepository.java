package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CriminalRepository {

    private List<CriminalEntity> earthCriminalsDatabase = Arrays.asList(
            new CriminalEntity("JEAN", "DUPONT", false),
            new CriminalEntity("SIMON", "DUPONT", true),
            new CriminalEntity("ARNAUD", "DURANT", true)
    );

    /**
     * Create an ID and a user then return the ID
     * @param userId
     * @return
     */
    public Optional<CriminalEntity> findCriminalByCriteria(String firstName, String lastName) {
        // check firstname and lastname not null
        return earthCriminalsDatabase.stream()
                .filter(criminal -> firstName.equalsIgnoreCase(criminal.getFirstName()) && lastName.equalsIgnoreCase(criminal.getLastName()))
                .findFirst();
    }


}
