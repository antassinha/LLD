package org.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.test.models.ReadResponse;
import org.test.policy.impl.LRUEvictionPolicy;
import org.test.provider.CacheProvider;
import org.test.provider.impl.DefaultLevelCache;
import org.test.provider.impl.NullCacheLevel;
import org.test.storage.impl.InmemoryStorage;


class CacheServiceTest {

    @Test
    public void testLevelCache() {
        CacheProvider c1 = createCache(10);
        CacheProvider c2 = createCache(10);

        DefaultLevelCache l2Cache = new DefaultLevelCache(new NullCacheLevel(), c2);
        DefaultLevelCache l1Cache = new DefaultLevelCache(l2Cache, c1);

        CacheService cacheService = new CacheService(l1Cache);

        cacheService.set("k1", "v1");
        cacheService.set("k2", "v2");

        ReadResponse<String> r1 = cacheService.get("k1");
        ReadResponse<String> x2 = cacheService.get("k2");
        System.out.println("r2 is " + x2.getValue());
        System.out.println("r1 value is " + r1.getValue());
        //Assertions.assertEquals("v1", r1.getValue());

        ReadResponse<String> r2 = cacheService.get("k2");
        Assertions.assertEquals("v2", r2.getValue());

        // Explicitly remove key from l1 for testing.
        c1.set("k1", null);

        ReadResponse<String> r1AfterRemovalFromL1 = cacheService.get("k1");
        Assertions.assertEquals("v1", r1AfterRemovalFromL1.getValue());

        ReadResponse<String> reRead = cacheService.get("k1");
        Assertions.assertEquals("v1", reRead.getValue());

        boolean set = cacheService.set("k1", "v1");
        System.out.println("set is " + set);
    }

    private CacheProvider createCache(int capacity) {
        return new CacheProvider(
                new LRUEvictionPolicy(),
                new InmemoryStorage(capacity));
    }

}