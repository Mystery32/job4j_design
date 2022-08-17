package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("sphere")
                .contains("phere")
                .doesNotContain("spheric")
                .startsWith("Sph")
                .startsWithIgnoringCase("sph")
                .endsWith("ere")
                .isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(6, 5);
        String name = box.whatsThis();
        assertThat(name).isNotNull()
                .isNotEmpty()
                .isNotBlank()
                .containsIgnoringCase("cube")
                .contains("ub")
                .doesNotContain("cubic")
                .startsWith("Cu")
                .startsWithIgnoringCase("cu")
                .endsWith("be")
                .isEqualTo("Cube");
    }

    @Test
    void isFourVertex() {
        Box box = new Box(4, 8);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isNotZero()
                .isPositive()
                .isEven()
                .isGreaterThan(3)
                .isLessThan(5)
                .isEqualTo(4);
    }

    @Test
    void isZeroVertex() {
        Box box = new Box(0, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isZero()
                .isGreaterThan(-1)
                .isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isExist() {
        Box box = new Box(4, 8);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void isNotExist() {
        Box box = new Box(3, 10);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void isAreaOfSphere() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(1256.64d, withPrecision(0.01d))
                .isCloseTo(1256.63d, withPrecision(0.01d))
                .isCloseTo(1256.63d, Percentage.withPercentage(1.0d))
                .isGreaterThan(1256.637d)
                .isLessThan(1256.6371d);
    }

    @Test
    void isAreaOfTetrahedron() {
        Box box = new Box(4, 8);
        double result = box.getArea();
        assertThat(result).isEqualTo(110.8512d, withPrecision(0.00006d))
                .isCloseTo(110.85125d, withPrecision(0.00001d))
                .isCloseTo(110.851d, Percentage.withPercentage(1.0d))
                .isGreaterThan(110.851d)
                .isLessThan(110.8513d);
    }
}
