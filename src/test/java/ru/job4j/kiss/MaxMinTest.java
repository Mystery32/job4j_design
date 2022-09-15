package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenMax() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 12, 1);
        assertThat(maxMin.max(list, Integer::compare)).isEqualTo(12);
    }

    @Test
    void whenMin() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 12, 1);
        assertThat(maxMin.min(list, Integer::compare)).isEqualTo(1);
    }

    @Test
    void whenEmptyList() {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of();
        assertThatThrownBy(() -> maxMin.min(list, Integer::compare))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("st is emp");
    }

}