package com.absys.test.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CollectionsHelperTest {

    @Test
    void sanitize_convertToTreeMap_test() {
        assertNotNull(CollectionsHelper.convertToTreeMap(null));
    }
}