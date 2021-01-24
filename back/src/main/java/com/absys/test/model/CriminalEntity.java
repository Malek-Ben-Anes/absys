package com.absys.test.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Data
public class CriminalEntity {

    private String firstName;
    private String lastName;
    private Date birthDate;

    public CriminalEntity(@NotEmpty String firstName, @NotEmpty String lastName, @NotNull Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
