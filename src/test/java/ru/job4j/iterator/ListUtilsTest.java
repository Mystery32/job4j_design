package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenDeleteAtPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        Predicate<Integer> pred = i -> i > 3;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenReplaceAtPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 1, 0));
        Predicate<Integer> pred = i -> i == 0;
        ListUtils.replaceIf(input, pred, 1);
        assertThat(input, is(Arrays.asList(1, 1, 1, 1, 1, 1)));
    }

    @Test
    public void whenDeleteAtList() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        List<Integer> delete = new ArrayList<>(Arrays.asList(2, 4, 6));
        ListUtils.removeAll(input, delete);
        assertThat(input, is(Arrays.asList(0, 1, 3, 5, 7)));
    }
}
