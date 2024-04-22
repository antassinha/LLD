package org.test.provider;

import org.test.models.ReadResponse;

/**
 * Interface to define functionality at a cache level
 */
public interface CacheLevel {
    Boolean set(String key, String value);


    ReadResponse<String> get(String key);

    Boolean remove(String key);

}
