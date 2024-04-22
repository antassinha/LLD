package org.test.storage.impl;

import org.test.exceptions.StorageFullException;
import org.test.storage.Storage;

import java.util.HashMap;
import java.util.Map;


/**
 * In memory implementation of the Storage class
 * uses hashMap for storing data
 */
public class InmemoryStorage implements Storage {

    private final Map<String, String> storage;
    private final Integer capacity;

    public InmemoryStorage(Integer capacity) {
        this.storage = new HashMap();
        this.capacity = capacity;
    }

    private boolean isStorageFull() {
        return storage.size() == capacity;
    }

    
    @Override
    public void add(String key, String value) throws StorageFullException {
        if (isStorageFull()) {
            throw new StorageFullException();
        }

        storage.put(key, value);
        
    }

    @Override
    public String remove(String key) {
        return storage.remove(key);

    }

    @Override
    public String get(String key) {
        return storage.get(key);
    }
}
