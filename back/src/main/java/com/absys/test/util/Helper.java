package com.absys.test.util;

import java.util.Map;
import java.util.TreeMap;

public class Helper {

    private Helper() {
    }

    /**
     * Convert an unordered map to an ordered tree map.
     */
    public static <K, V, W> Map<K, Map<V, W>> convertToTreeMap(Map<K, Map<V, W>> hashMap) {
        Map<K, Map<V, W>> treeMap = new TreeMap<>();
        if (hashMap != null && !hashMap.isEmpty()) {
            treeMap.putAll(hashMap);
            hashMap.forEach((k, v) -> {
                if (v != null && !v.isEmpty()) {
                    v = new TreeMap<>(v);
                }
            });
        }
        return treeMap;
    }
}
