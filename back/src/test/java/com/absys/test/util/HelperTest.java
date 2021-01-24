package com.absys.test.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class HelperTest {

    @Test
    void generateId() {
        assertNotNull(Helper.convertToTreeMap(null));
    }
}