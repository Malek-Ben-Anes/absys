package com.absys.test.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CollectionsHelper {

    private CollectionsHelper() {
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

    /**
     * Convert an unordered map to an ordered tree map.
     */
    public static <V, W> Map<String, Map<V, W>> sortMap(Map<String, Map<V, W>> map) {
        return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }
}
