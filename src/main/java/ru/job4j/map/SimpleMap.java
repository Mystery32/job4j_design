package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        expand();
        if (table[indexFor(key.hashCode())] == null) {
            table[indexFor(key.hashCode())] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        int sum = 0;
        for (int i = hashCode; i != 0; i /= 10) {
            sum += (i % 10);
        }
        return sum % table.length;
    }

    private int hash1(int hashCode) {
        return (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return hash(hash) & (table.length - 1);
    }

    private void expand() {
        if ((float) count / table.length >= LOAD_FACTOR) {
            table = Arrays.copyOf(table, table.length * 2);
        }
    }

    @Override
    public V get(K key) {
        Objects.checkIndex(indexFor(key.hashCode()), table.length);
        V v = null;
        if (table[indexFor(key.hashCode())] != null) {
            v = table[indexFor(key.hashCode())].value;
        }
        return v;
    }

    @Override
    public boolean remove(K key) {
        Objects.checkIndex(indexFor(key.hashCode()), table.length);
        boolean rsl = false;
        if (table[indexFor(key.hashCode())] != null) {
            table[indexFor(key.hashCode())] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            int iterCount;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (iterCount < capacity - 1 && table[iterCount] == null) {
                    iterCount++;
                }
                return table[iterCount] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[iterCount++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
