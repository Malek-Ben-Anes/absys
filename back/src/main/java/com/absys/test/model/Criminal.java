package com.absys.test.model;

import java.util.Arrays;
import java.util.List;

public class Criminal {
    private String firstname;
    private String lastname;
    private boolean notAllowedToMars;

    public static List<Criminal> earthCriminal() {
        return Arrays.asList(
                new Criminal("JEAN", "DUPONT", false),
                new Criminal("SIMON", "DUPONT", true),
                new Criminal("ARNAUD", "DURANT", true)
        );
    }

    public Criminal(String firstname, String lastname, boolean notAllowedToMars) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.notAllowedToMars = notAllowedToMars;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isNotAllowedToMars() {
        return notAllowedToMars;
    }

    public void setNotAllowedToMars(boolean notAllowedToMars) {
        this.notAllowedToMars = notAllowedToMars;
    }
}
