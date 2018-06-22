package com.gubin.MyArrayList;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private Object[] elements;
    private int size;
    private int capacity = 10;


    public MyArrayList() {
        elements = new Object[capacity];
        size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("размер MyArrayList не может быть отрицaтельным");
        }
        this.capacity = capacity;
        elements = new Object[capacity];
        size = 0;
    }

    private void resizeArray(int newSize) {
        Object[] newArray = new Object[newSize];
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object e : elements) {
            if (Objects.equals(e, o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return listIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size);
        return result;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        } else {
            //noinspection SuspiciousSystemArraycopy
            System.arraycopy(elements, 0, a, 0, size);
        }
        return a;
    }

    @Override
    public boolean add(T element) {
        if (elements.length == size) {
            resizeArray(elements.length + 1);
        }
        elements[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                System.arraycopy(elements, i + 1, elements, i, size - i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            throw new IllegalArgumentException("перeдаваемая коллекция равна null");
        }
        for (Object elem : c) {
            if (!this.contains(elem)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c != null) {
            for (T elem : c) {
                this.add(elem);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object addedArray = c.toArray();
        int addedLength = ((Object[]) addedArray).length;

        if (addedLength == 0) {
            return false;
        }
        if ((elements.length - size) < addedLength) {
            resizeArray(size + addedLength);
        }

        int numMove = size - index;
        if (numMove > 0) {
            System.arraycopy(elements, index, elements, index + addedLength, numMove);
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(addedArray, 0, elements, index, addedLength);
        size += addedLength;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        boolean hasDeletion = false;
        for (Object e : c) {
            while (this.contains(e)) {
                remove(e);
                hasDeletion = true;
            }
        }
        return hasDeletion;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasDeletion = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                i--;
                hasDeletion = true;
            }
        }
        return hasDeletion;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
    }

    @Override
    public T get(int index) {
        //noinspection unchecked
        return (T) elements[index];
    }

    @Override
    public T set(int index, T element) {
        elements[index] = element;
        //noinspection unchecked
        return (T) elements[index];
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        if (size == elements.length) {
            resizeArray(elements.length + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        //noinspection unchecked
        T elem = (T) elements[index];
        if (index == size - 1) {
            elements[index] = null;
            size--;
            return elem;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index);
        size--;
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (elements[i].equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
