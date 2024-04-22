package org.test.provider.impl;

import org.test.models.ReadResponse;
import org.test.provider.CacheLevel;
import org.test.provider.CacheProvider;

/**
 * The default implementation of Cache Level
 * Holds cache storage and
 * Holds access to the next cache level
 */
public class DefaultLevelCache implements CacheLevel {

    private final CacheLevel next;
    private final CacheProvider cacheProvider;

    public DefaultLevelCache(CacheLevel next, CacheProvider cacheProvider) {
        this.next = next;
        this.cacheProvider = cacheProvider;
    }

    @Override
    public Boolean set(String key, String value) {
        String curLevelValue = cacheProvider.get(key);
        if (!value.equals(curLevelValue)) {
            cacheProvider.set(key, value);
        }
        next.set(key, value);
        return true;
    }

    @Override
    public ReadResponse get(String key) {
        String curLevelValue = cacheProvider.get(key);

        // L1 -> L2 -> L3 -> L4
        if (curLevelValue == null) {
            ReadResponse<String> nextResponse = next.get(key);
            curLevelValue = nextResponse.getValue();
            if (curLevelValue != null) {
                cacheProvider.set(key, curLevelValue);
            }
        }
        return new ReadResponse<>(curLevelValue);
    }

    @Override
    public Boolean remove(String key) {

        String curLevelValue = cacheProvider.get(key);

        // L1 -> L2 -> L3 -> L4
        if (curLevelValue != null) {
            cacheProvider.remove(key);
            next.remove(key);
        }
        return true;
    }
}
