package com.absys.test.repository;

import com.absys.test.model.CriminalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CriminalRepository {

    private final DatabaseStorage storage;

    /**
     * Find a criminal by his firstName and Lastname
     *
     * @param firstName
     * @param lastName
     * @return Optional<CriminalEntity>
     */
    public Optional<CriminalEntity> findCriminalByCriteria(String firstName, String lastName, Date birthDate) {
        return storage.getEarthCriminals().stream()
                .filter(criminal -> criminal.getFirstName().equalsIgnoreCase(firstName)
                        && criminal.getLastName().equalsIgnoreCase(lastName)
                        && criminal.getBirthDate().equals(birthDate))
                .findFirst();
    }
}
