package ru.job4j.serialization.json;

import java.util.Arrays;

public class Student {

    private final boolean expelled;
    private final int age;
    private final String name;
    private final FavoriteLesson favoriteLesson;
    private final String[] sections;

    public Student(boolean expelled, int age, String name, FavoriteLesson favoriteLesson, String[] sections) {
        this.expelled = expelled;
        this.age = age;
        this.name = name;
        this.favoriteLesson = favoriteLesson;
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Student{"
                + "expelled="
                + expelled + ", age="
                + age + ", name='" + name
                + '\'' + ", FavoriteLesson="
                + favoriteLesson + ", sections="
                + Arrays.toString(sections)
                +
                '}';
    }
}
