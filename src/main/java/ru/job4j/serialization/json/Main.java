package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        JSONObject jsonFavoriteLesson = new JSONObject("{\"name\":\"Физика\",\"grade\":\"92\"}");

        List<String> list = new ArrayList<>();
        list.add("Шахматы");
        list.add("Бильярд");
        JSONArray jsonSections = new JSONArray(list);

        final Student student = new Student(false, 19, "Иван",
                new FavoriteLesson("Математика", 87), new String[]{"Бокс", "Туризм"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("expelled", student.isExpelled());
        jsonObject.put("age", student.getAge());
        jsonObject.put("name", student.getName());
        jsonObject.put("favoriteLesson", jsonFavoriteLesson);
        jsonObject.put("sections", jsonSections);

        System.out.println(jsonObject);

        System.out.println(new JSONObject(student));
    }
}
