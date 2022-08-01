package ru.job4j.serialization.json;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "favoriteLesson")
@XmlAccessorType(XmlAccessType.FIELD)
public class FavoriteLesson {

    @XmlAttribute
    private String name;
    @XmlAttribute
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

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }
}
