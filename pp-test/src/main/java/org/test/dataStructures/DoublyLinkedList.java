package org.test.dataStructures;

import org.test.exceptions.InvalidElementException;

import java.util.NoSuchElementException;

/**
 * A doubly linked list to implement the LFU functionality
 */
public class DoublyLinkedList {
    DoublyLinkedListNode dummyHead;
    DoublyLinkedListNode dummyTail;

    public DoublyLinkedList() {
        // We can instantiate these by null, since we are never gonna use val for these dummyNodes
        dummyHead = new DoublyLinkedListNode(null);
        dummyTail = new DoublyLinkedListNode(null);

        // Also Initially there are no items
        // so just join dummyHead and Tail, we can add items in between them easily.
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DoublyLinkedListNode node) {
        // Just Simply modifying the pointers.
        if (node != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode node) {
        DoublyLinkedListNode tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }


    public DoublyLinkedListNode addElementAtLast(String element) throws InvalidElementException {
        if (element == null) {
            throw new InvalidElementException();
        }
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    public DoublyLinkedListNode getFirstNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}
