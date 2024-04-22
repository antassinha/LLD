package org.test.service;

import org.test.models.ReadResponse;
import org.test.provider.CacheLevel;
import org.test.provider.impl.DefaultLevelCache;


/**
 * The cache service which can be used by the client to access cache operations
 */
public class CacheService {

    private CacheLevel multiLevelCache;
    public CacheService(final DefaultLevelCache multiLevelCache) {
        this.multiLevelCache = multiLevelCache;
    }

    public boolean set(final String key, final String value) {
        return multiLevelCache.set(key, value);
    }

    public ReadResponse<String> get(final String key) {
        final ReadResponse<String> readResponse = multiLevelCache.get(key);
        return readResponse;
    }
}
