package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void arrayExpansion() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            arrayExpansion();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        if (Objects.checkIndex(index, size) >= 0) {
            T t = container[index];
            container[index] = newValue;
            return t;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T remove(int index) {
        if (Objects.checkIndex(index, size) >= 0) {
            T t = container[index];
            System.arraycopy(container, index + 1, container, index, size - index - 1);
            container[size - 1] = null;
            size--;
            modCount++;
            return t;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public T get(int index) {
        if (Objects.checkIndex(index, size) >= 0) {
            return container[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {

            int iterSize;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iterSize < size && size != 0;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[iterSize++];
            }
        };
    }
}
