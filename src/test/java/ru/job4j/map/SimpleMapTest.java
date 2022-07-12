package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    SimpleMap<String, Integer> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put("First", 1);
        map.put("Second", 2);
        map.put("Third", 3);
    }

    @Test
    public void whenCorrectPut() {
        assertTrue(map.put("Fourth", 4));
    }

    @Test
    public void whenIncorrectPut() {
        assertFalse(map.put("Third", 3));
    }

    @Test
    public void whenGetByCorrectIndex() {
        assertThat(map.get("First"), is(1));
    }

    @Test
    public void whenGetByIncorrectIndex() {
        assertNull(map.get("Fourth"));
    }

    @Test
    public void whenRemoveCorrectItem() {
        assertTrue(map.remove("Second"));
    }

    @Test
    public void whenRemoveIncorrectItem() {
        assertFalse(map.remove("Fourth"));
    }

    @Test
    public void whenIteratorHasNext() {
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        map = new SimpleMap<>();
        map.put("First", 1);
        Assert.assertEquals("First", map.iterator().next());
        Assert.assertEquals("First", map.iterator().next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMapThenNextThrowException() {
        map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.put("Fourth", 4);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.remove("Second");
        iterator.next();
    }
}
