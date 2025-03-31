package com.aston;

import com.aston.MyLinkedList.Node;

import java.util.Comparator;

public class LinkedListMergeSort<T> {

    public LinkedListMergeSort() {}

    Node<T> split(Node<T> head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node<T> slow = head;
        Node<T> fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node<T> second = slow.next;
        slow.next = null;
        return second;
    }

    Node<T> mergeSort(Node<T> head, Comparator<? super T> comparator) {
        if (head == null || head.next == null) {
            return head;
        }
        Node<T> second = split(head);
        head = mergeSort(head, comparator);
        second = mergeSort(second, comparator);
        return merge(head, second, comparator);
    }

    Node<T> merge(Node<T> first, Node<T> second, Comparator<? super T> comparator) {
        if (first == null) return second;
        if (second == null) return first;

        if (comparator.compare(first.item, second.item) <= 0) {
            first.next = merge(first.next, second, comparator);
            if (first.next != null) {
                first.next.prev = first;
            }
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next, comparator);
            if (second.next != null) {
                second.next.prev = second;
            }
            second.prev = null;
            return second;
        }
    }
}
