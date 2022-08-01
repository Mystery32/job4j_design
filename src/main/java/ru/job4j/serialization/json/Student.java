package ru.job4j.serialization.json;

import javax.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean expelled;
    private int age;
    private String name;
    private FavoriteLesson favoriteLesson;
    @XmlElementWrapper(name = "sections")
    @XmlElement(name = "section")
    private String[] sections;

    public Student() {

    }

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

    public boolean isExpelled() {
        return expelled;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public FavoriteLesson getFavoriteLesson() {
        return favoriteLesson;
    }

    public String[] getSections() {
        return sections;
    }
}
