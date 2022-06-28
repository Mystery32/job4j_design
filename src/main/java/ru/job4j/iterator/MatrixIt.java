package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (column == data[row].length) {
            row++;
            column = 0;
        }
        if (row < data.length) {
            while (data[row].length == 0) {
                row++;
                if (row >= data.length) {
                    return false;
                }
            }
            return (column < data[row].length);
        }
        return false;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}
