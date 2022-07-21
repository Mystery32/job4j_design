package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        Files.walkFileTree(Path.of("C:\\projects\\job4j_design\\test_dir"), new DuplicatesVisitor());
    }
}
