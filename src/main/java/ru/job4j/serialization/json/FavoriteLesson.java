package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "favoriteLesson")
public class FavoriteLesson {

    @XmlAttribute
    private String name;
    private int grade;

    public FavoriteLesson() {

    }

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
