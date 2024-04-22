package org.test.policy.impl;

import org.test.dataStructures.DoublyLinkedList;
import org.test.dataStructures.DoublyLinkedListNode;
import org.test.exceptions.InvalidElementException;
import org.test.policy.EvictionPolicy;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU eviction policy
 * uses doubly linked list to maintain the frequency of the keys
 */
public class LRUEvictionPolicy implements EvictionPolicy {
    private DoublyLinkedList dll;
    private Map<String, DoublyLinkedListNode> mapper;

    public LRUEvictionPolicy() {
        this.dll = new DoublyLinkedList();
        this.mapper = new HashMap<>();
    }


    /**
     * @param key
     * adds the key to the tail of the doubly linked list
     */
    @Override
    public void keyAccessed(String key) {
        if (mapper.containsKey(key)) {
            dll.detachNode(mapper.get(key));
            dll.addNodeAtLast(mapper.get(key));
        } else {
            try {
                DoublyLinkedListNode newNode = dll.addElementAtLast(key);
                mapper.put(key, newNode);
            } catch (InvalidElementException e) {
                System.out.println("Got an invalid access exception while cache poliy keyAccessed");
                e.printStackTrace();
            }
        }

    }

    /**
     * @return first node from the head
     */
    @Override
    public String evictKey() {
        DoublyLinkedListNode first = dll.getFirstNode();
        if(first == null) {
            return null;
        }
        dll.detachNode(first);
        return first.getElement();
    }
}
