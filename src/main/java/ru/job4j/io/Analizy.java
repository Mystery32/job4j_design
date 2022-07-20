package ru.job4j.io;

import java.io.*;
import java.util.List;

public class Analizy {

    public static void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean active = true;
            List<String> list = read.lines().toList();
            for (String s : list) {
                if (active && Integer.parseInt(s.split(" ")[0].trim()) == 400
                        || Integer.parseInt(s.split(" ")[0].trim()) == 500) {
                    out.print(s.split(" ")[1].trim() + ";");
                    active = false;
                } else if (!active && Integer.parseInt(s.split(" ")[0].trim()) == 200
                        || Integer.parseInt(s.split(" ")[0].trim()) == 300) {
                    out.print(s.split(" ")[1].trim() + ";" + System.lineSeparator());
                    active = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("server.log", "unavailable.csv");
    }
}
