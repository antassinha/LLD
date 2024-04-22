package org.test.storage;

import org.test.exceptions.StorageFullException;

/**
 * Interface to define the cache storage functions
 */
public interface Storage {

    void add(String key, String value) throws StorageFullException;
    String remove(String key);
    String get(String key);
}
