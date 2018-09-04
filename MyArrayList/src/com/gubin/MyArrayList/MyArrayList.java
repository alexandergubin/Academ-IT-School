package com.gubin.MyArrayList;

import java.util.*;

@SuppressWarnings("unused")
public class MyArrayList<T> implements List<T> {
    private T[] elements;
    private int size;
    private int modCount = 0;

    public MyArrayList() {
        // noinspection unchecked
        elements = (T[]) new Object[10];
        size = 0;
    }

    @SuppressWarnings("WeakerAccess")
    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("размер MyArrayList не может быть отрицaтельным");
        }
        // noinspection unchecked
        elements = (T[]) new Object[capacity];
        size = 0;
    }

    private void enlargeArray(int newSize) {
        if (newSize < elements.length) {
            throw new IllegalArgumentException("некорректная длинна массива при увеличении");
        }
        elements = Arrays.copyOf(elements, newSize);
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
        return (this.indexOf(o) >= 0);
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(elements, size, a.getClass());
        }
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(elements, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(T element) {
        if (elements.length == size) {
            enlargeArray(elements.length * 3 / 2 + 1);
        }
        elements[size] = element;
        size++;
        modCount++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], element)) {
                if (i == size - 1) {
                    elements[i] = null;
                } else {
                    System.arraycopy(elements, i + 1, elements, i, size - i);
                }
                size--;
                modCount++;
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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }

        int addedLength = c.size();
        if (addedLength == 0) {
            return false;
        }
        if ((elements.length - size) < addedLength) {
            enlargeArray(size + addedLength);
        }

        int numMove = size - index;
        if (numMove > 0) {
            System.arraycopy(elements, index, elements, index + addedLength, numMove);
        }
        int i = index;
        for (T e : c) {
            elements[i] = e;
            i++;
        }
        size += addedLength;
        modCount++;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        boolean hasDeletion = false;
        int i;
        for (Object e : c) {
            while ((i = this.indexOf(e)) >= 0) {
                remove(i);
                hasDeletion = true;
            }
        }
        if (hasDeletion) {
            modCount++;
        }
        return hasDeletion;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean hasDeletion = false;
        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(elements[i])) {
                remove(i);
                hasDeletion = true;
            }
        }
        if (hasDeletion) {
            modCount++;
        }
        return hasDeletion;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        modCount++;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        return elements[index];
    }

    @Override
    public T set(int index, T element) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        T result = elements[index];
        elements[index] = element;
        return result;
    }

    @Override
    public void add(int index, T element) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        if (size == elements.length) {
            enlargeArray(elements.length * 3 / 2 + 1);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        T elem = elements[index];
        if (index == size - 1) {
            elements[index] = null;
            size--;
            modCount++;
            return elem;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        size--;
        modCount++;
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(elements[i], o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (elements[i] == null) {
                sb.append("null");
            } else {
                sb.append(elements[i].toString());
            }
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new ListItr(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if (index >= size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("некорректный index, выход за границы списка");
        }
        return new ListItr(index);
    }

    private class Itr implements Iterator<T> {
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount; //запоминаем число модификаций

        private Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public T next() {
            checkForComodification();
            if (cursor >= size) {
                throw new NoSuchElementException("коллеция закончилась");
            }
            Object[] elements = MyArrayList.this.elements;
            lastRet = cursor;
            cursor++;
            return (T) elements[lastRet];
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            MyArrayList.this.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = modCount;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends Itr implements ListIterator<T> {

        ListItr(int index) {
            super();
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return (cursor > 0 && cursor <= size);
        }

        @Override
        @SuppressWarnings("unchecked")
        public T previous() {
            checkForComodification();
            if (lastRet < 1) {
                throw new NoSuchElementException("не возможно получить предыдущий элемент у первого");
            }
            lastRet--;
            cursor--;
            return elements[lastRet];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void set(T t) {
            checkForComodification();
            MyArrayList.this.set(lastRet, t);
        }

        @Override
        public void add(T t) {
            checkForComodification();
            MyArrayList.this.add(cursor, t);
            cursor++;
            lastRet = -1;
            expectedModCount = modCount;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > elements.length) {
            throw new ArrayIndexOutOfBoundsException("индекс выходит за границы массива");
        }
        List<T> result = new ArrayList<>(toIndex - fromIndex);
        result.addAll(Arrays.asList(elements).subList(fromIndex, toIndex));
        return result;
    }

}
