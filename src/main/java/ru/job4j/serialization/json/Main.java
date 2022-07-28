package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) {

        final Student student = new Student(false, 19, "Иван",
                new FavoriteLesson("Математика", 87), new String[]{"Бокс", "Туризм"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        final String studentFromJson =
                "{"
                        + "\"expelled\":false,"
                        + "\"age\":19,"
                        + "\"name\":Иван,"
                        + "\"favoriteLesson\":"
                        + "{"
                        + "\"name\":\"Математика\""
                        + "\"grade\":\"87\""
                        + "},"
                        + "\"sections\":"
                        + "[\"Бокс\",\"Туризм\"]"
                        + "}";
        final Student st = gson.fromJson(studentFromJson, Student.class);
        System.out.println(st);
    }
}
