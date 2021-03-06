package com.gubin.listtask;

import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int size;

    public SinglyLinkedList() {
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public ListItem<T> getFirst() {
        return head;
    }

    private ListItem<T> getElement(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("некорректное значение index");
        }
        if (index == 0) {
            return head;
        }
        ListItem<T> item = head;
        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }
        return item;
    }

    public T getData(int index) {
        return this.getElement(index).getData();
    }


    public T setData(int index, T data) {
        ListItem<T> p = getElement(index);
        T tmp = p.getData();
        p.setData(data);
        return tmp;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("некорректное значение index");
        }
        if (index == 0) {
            return removeFirst();
        }
        ListItem<T> p = getElement(index - 1);
        T result = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        size--;
        return result;
    }

    public boolean remove(T data) {
        if (size == 0) {
            throw new NullPointerException("список пуст");
        }
        if (Objects.equals(head.getData(), data)) {
            removeFirst();
            return true;
        }
        for (ListItem<T> prev = head, p = head.getNext(); p != null; prev = p, p = p.getNext()) {
            if (Objects.equals(p.getData(), data)) {
                prev.setNext(p.getNext());
                size--;
                return true;
            }
        }
        return false;
    }

    public T removeFirst() {
        if (size == 0) {
            throw new NullPointerException("список пуст");
        }
        T result = head.getData();
        head = head.getNext();
        size--;
        return result;
    }

    public void addFirst(T data) {
        ListItem<T> p = new ListItem<>(data);
        if (head != null) {
            p.setNext(head);
            head = p;
        } else {
            head = p;
            head.setNext(null);
        }
        size++;
    }

    public void addLast(T data) {
        add(size, data);
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("некорректное значение index");
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        ListItem<T> p = getElement(index - 1);
        ListItem<T> node = new ListItem<>(data);
        node.setNext(p.getNext());
        p.setNext(node);
        size++;
    }

    public SinglyLinkedList<T> reverseList() {
        ListItem<T> reversedPart = null;
        ListItem<T> current = head;
        while (current != null) {
            ListItem<T> next = current.getNext();
            current.setNext(reversedPart);
            reversedPart = current;
            current = next;
        }
        head = reversedPart;
        return this;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> result = new SinglyLinkedList<>();
        result.size = this.size;

        ListItem<T> p = head;
        ListItem<T> previous = null;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                previous = new ListItem<>(p.getData());
                result.head = previous;
            } else {
                ListItem<T> addedItemNext = new ListItem<>(p.getData());
                previous.setNext(addedItemNext);
                previous = previous.getNext();
            }
            p = p.getNext();
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        ListItem<T> p = head;
        for (int i = 0; i < size; i++) {
            if (p.getData() == null) {
                sb.append("null");
            } else {
                sb.append(p.getData().toString());
            }
            if (i < size - 1) {
                sb.append(", ");
            }
            p = p.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}
