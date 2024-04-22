package org.test.policy;

/**
 * Interface to describe the eviction policy
 */
public interface EvictionPolicy {
    void keyAccessed(String key);
    String evictKey();
}
