package org.test.dataStructures;

import org.test.exceptions.InvalidElementException;

/**
 * Node for doubly linked list to maintain key occurance frequency
 */
public class DoublyLinkedListNode {
    DoublyLinkedListNode next;
    DoublyLinkedListNode prev;
    String element;

    public DoublyLinkedListNode(String element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }

    public String getElement() {
        return element;
    }
}
