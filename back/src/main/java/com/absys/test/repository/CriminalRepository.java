package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import com.absys.test.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CriminalRepository {

    private final DatabaseStorage storage;

    /**
     * Create an ID and a user then return the ID
     * @param  firstName
     * @param  lastName
     * @return Optional<CriminalEntity>
     */
    public Optional<CriminalEntity> findCriminalByCriteria(String firstName, String lastName) {
        // check firstname and lastname not null
        return storage.getEarthCriminals().stream()
                .filter(criminal -> firstName.equalsIgnoreCase(criminal.getFirstName()) && lastName.equalsIgnoreCase(criminal.getLastName()))
                .findFirst();
    }


}
