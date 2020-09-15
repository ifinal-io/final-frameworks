

package org.finalframework.util;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author likly
 * @version 1.0
 * @date 2019-12-17 12:53:41
 * @since 1.0
 */
public class LinkedMultiKeyMap<K1, K2, V> extends LinkedHashMap<K1, Map<K2, V>> implements MultiKeyMap<K1, K2, V> {

    @Override
    public void add(K1 key1, K2 key2, V value) {
        Map<K2, V> map = computeIfAbsent(key1, k -> new LinkedHashMap<>());
        map.put(key2, value);
    }

    @Override
    public V getOrDefault(K1 key1, K2 key2, V defValue) {
        Map<K2, V> map = get(key1);
        return map == null ? defValue : map.getOrDefault(key2, defValue);
    }
}
