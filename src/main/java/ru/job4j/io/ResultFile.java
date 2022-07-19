package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiplication table.txt")) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    out.write(String.valueOf((i + 1) * (j + 1)).getBytes());
                    out.write(" ".getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
