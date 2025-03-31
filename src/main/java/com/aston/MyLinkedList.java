package com.aston;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> {
    private int size = 0;
    private Node<T> head;
    private Node<T> tail;

    public MyLinkedList() {
    }


    public boolean add(T t) {
        linkLast(t);
        return true;
    }

    public boolean addToStart(T t) {
        linkFirst(t);
        return true;
    }

    public void add(int index, T t) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            linkLast(t);
        } else {
            Node<T> succ = node(index);
            linkBefore(t, succ);
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return unlink(node(index));
    }

    public boolean remove(T t) {
        Node<T> current = head;
        while (current != null) {
            if (current.item.equals(t)) {
                unlink(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.item = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = tail = null;
        size = 0;
    }

    public T getHead() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.item;
    }

    public T getTail() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return node(index).item;
    }

    public T set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> x = node(index);
        T oldValue = x.item;
        x.item = element;
        return oldValue;
    }

    public boolean contains(T t) {
        Node<T> current = head;
        while (current != null) {
            if (current.item.equals(t)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public int indexOf(T t) {
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (current.item.equals(t)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void sort(Comparator<? super T> comparator) {
        if (size < 2) return;
        LinkedListMergeSort<T> sorter = new LinkedListMergeSort<>();
        head = sorter.mergeSort(head, comparator);

        tail = head;
        while (tail != null && tail.next != null) {
            tail = tail.next;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


    private void linkFirst(T t) {
        final Node<T> f = head;
        final Node<T> newNode = new Node<>(null, t, f);
        head = newNode;
        if (f == null) tail = newNode;
        else f.prev = newNode;
        size++;
    }

    private void linkLast(T t) {
        final Node<T> l = tail;
        final Node<T> newNode = new Node<>(l, t, null);
        tail = newNode;
        if (l == null) head = newNode;
        else l.next = newNode;
        size++;
    }

    private void linkBefore(T t, Node<T> succ) {
        final Node<T> pred = succ.prev;
        final Node<T> newNode = new Node<>(pred, t, succ);
        succ.prev = newNode;
        if (pred == null) head = newNode;
        else pred.next = newNode;
        size++;
    }

    private T unlink(Node<T> x) {
        final T element = x.item;
        final Node<T> next = x.next;
        final Node<T> prev = x.prev;

        if (prev != null) {
            prev.next = next;
        } else {
            head = next;
        }

        if (next != null) {
            next.prev = prev;
        } else {
            tail = prev;
        }

        x.item = null;
        x.next = null;
        x.prev = null;
        size--;
        return element;
    }


    private Node<T> node(int index) {
        Node<T> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }


    protected static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}