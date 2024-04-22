package org.test.provider;

import org.test.exceptions.StorageFullException;
import org.test.policy.EvictionPolicy;
import org.test.storage.Storage;

/**
 * The actual cache storage and metadata handling class
 * has the storage
 * and the eviction workflows
 */
public class CacheProvider {

    private final EvictionPolicy evictionPolicy;
    private final Storage storage;

    public CacheProvider(EvictionPolicy evictionPolicy, Storage storage) {
        this.evictionPolicy = evictionPolicy;
        this.storage = storage;
    }

    public void set(String key, String value) {
        try {
            this.storage.add(key, value);
            this.evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception) {
            final String keyToRemove = evictionPolicy.evictKey();
            if (keyToRemove == null) {
                throw new RuntimeException("Unexpected State.");
            }

            this.storage.remove(keyToRemove);

            set(key, value);
        }
    }

    public String get(String key) {
        final String value = this.storage.get(key);
        this.evictionPolicy.keyAccessed(key);
        return value;
    }

    public String remove(String key) {
        return storage.remove(key);
    }

}