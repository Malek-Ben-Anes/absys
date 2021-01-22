package com.absys.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CriminalEntity {
    
    private String firstName;
    private String lastName;
    private boolean notAllowedToMars;
}
