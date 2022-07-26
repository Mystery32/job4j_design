package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String outFile = argsName.get("out");
        String[] filter = argsName.get("filter").split(",");
        validate(argsName.sizeMapOfNames(), path, delimiter, outFile, filter);

        int[] indexFilterColumns = new int[filter.length];
        StringBuilder sb = new StringBuilder();

        try (var scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(delimiter);
                for (int i = 0; i < filter.length; i++) {
                    for (int j = 0; j < data.length; j++) {
                        if (filter[i].equals(data[j])) {
                            indexFilterColumns[i] = j;
                        }
                    }
                }
                for (int i: indexFilterColumns) {
                    sb.append(data[i]).append(delimiter);
                }
                sb.deleteCharAt(sb.length() - 1)
                        .append(System.lineSeparator());
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile))) {
            pw.print(sb);
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(int size, Path path, String delimiter, String outFile, String[] filter) {
        if (size != 4) {
            throw new ArrayIndexOutOfBoundsException("Invalid number of parameters entered. Enter four options!");
        }
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("An invalid file name was entered for reading data or the file is missing. "
                    + "Enter a valid filename for reading data!");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("The delimiter does not match the required format!");
        }
        if (filter.length == 0) {
            throw new IllegalArgumentException("Filtering parameters by columns of the source file are missing or incorrectly entered!");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
