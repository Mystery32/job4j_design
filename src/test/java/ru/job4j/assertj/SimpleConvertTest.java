package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleConvertTest {

    @Test
    public void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).isNotNull()
                .hasSize(5)
                .contains("second")
                .containsOnly("first", "three", "five", "four", "second")
                .containsAnyOf("zero", "three", "six")
                .doesNotContain("seven")
                .anySatisfy(e -> assertThat(e).contains("ive"))
                .noneMatch(e -> e.startsWith("w"));
        assertThat(list).first().isEqualTo("first");
        assertThat(list).element(2).isNotNull().isEqualTo("three");
        assertThat(list).filteredOn(e -> e.startsWith("f"))
                .hasSize(3)
                .last().isEqualTo("five");
    }

    @Test
    public void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five");
        assertThat(set).isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .anyMatch(e -> e.length() > 3);
        assertThat(set).filteredOn(e -> e.startsWith("f"))
                .hasSize(3);
    }

    @Test
    public void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five");
        assertThat(map).isNotNull()
                .hasSize(5)
                .containsKeys("first", "three", "second")
                .containsValues(1, 4, 3)
                .doesNotContainKey("seven")
                .doesNotContainValue(9);
    }
}
