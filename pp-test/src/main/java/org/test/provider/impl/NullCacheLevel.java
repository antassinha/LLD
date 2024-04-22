package org.test.provider.impl;

import org.test.models.ReadResponse;
import org.test.provider.CacheLevel;

/**
 * Null cache level for handling last cache level scenarios
 */
public class NullCacheLevel implements CacheLevel {
    @Override
    public Boolean set(String key, String value) {
        return true;
    }

    @Override
    public ReadResponse<String> get(String key) {
        return new ReadResponse<>("NULL VALUE");
    }

    @Override
    public Boolean remove(String key) {
        return true;
    }
}
