package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t1, t2) -> comparator.compare(t1, t2) > 0;
        return compareBy(value, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> predicate = (t1, t2) -> comparator.compare(t1, t2) < 0;
        return compareBy(value, predicate);
    }

    public <T> T compareBy(List<T> value, BiPredicate<T, T> predicate) {
        T result;
        if (value.size() == 0) {
            throw new IllegalArgumentException("List is empty");
        }
        result = value.get(0);
        for (T t: value) {
            if (predicate.test(t, result)) {
                result = t;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MaxMin maxMin = new MaxMin();
        List<Integer> list = List.of(4, 2, 12, 1);
        Comparator<Integer> comparator = Integer :: compare;
        System.out.println(maxMin.max(list, comparator));
        System.out.println(maxMin.min(list, comparator));
        List<String> list2 = List.of("привет", "пока", "день", "ночь");
        Comparator<String> comparator1 = String :: compareTo;
        System.out.println(maxMin.max(list2, comparator1));
        System.out.println(maxMin.min(list2, comparator1));
        List<Integer> list1 = List.of();
        System.out.println(maxMin.max(list1, comparator));
    }
}
