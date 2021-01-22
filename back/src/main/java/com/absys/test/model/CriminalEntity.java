package com.absys.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CriminalEntity {
    
    private String firstname;
    private String lastname;
    private boolean notAllowedToMars;
}
