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
     * Find a criminal by his firstName and Lastname
     * @param  firstName
     * @param  lastName
     * @return Optional<CriminalEntity>
     */
    public Optional<CriminalEntity> findCriminalByCriteria(String firstName, String lastName) {
        return storage.getEarthCriminals().stream()
                .filter(criminal -> criminal.getFirstName().equalsIgnoreCase(firstName) && criminal.getLastName().equalsIgnoreCase(lastName))
                .findFirst();
    }


}
