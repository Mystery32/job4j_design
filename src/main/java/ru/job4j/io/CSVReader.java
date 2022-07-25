package ru.job4j.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        Path path = Path.of(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String outFile = argsName.get("out");
        List<String> filter = Arrays.stream(argsName.get("filter").split(",")).toList();
        validate(argsName.sizeMapOfNames(), path, delimiter, filter);

        List<Integer> indexFilterColumns = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        try (var scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split(delimiter);
                for (int i = 0; i < data.length; i++) {
                    if (filter.contains(data[i])) {
                        indexFilterColumns.add(i);
                    }
                }
                for (int i : indexFilterColumns) {
                    sb.append(data[i]).append(delimiter);
                }
                sb.deleteCharAt(sb.length() - 1)
                        .append(System.lineSeparator());
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(outFile))) {
            pw.print(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(int size, Path path, String delimiter, List<String> filter) {
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
        if (filter.size() == 0) {
            throw new IllegalArgumentException("Filtering parameters by columns of the source file are missing or incorrectly entered!");
        }
    }
}
