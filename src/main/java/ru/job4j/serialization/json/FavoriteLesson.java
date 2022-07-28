package ru.job4j.serialization.json;

public class FavoriteLesson {

    private final String name;

    private final int grade;

    public FavoriteLesson(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "FavoriteLesson{"
                + "name='" + name
                + '\'' + ", grade="
                + grade + '}';
    }
}
