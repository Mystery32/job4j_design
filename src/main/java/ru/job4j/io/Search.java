package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static void validate(String[] args) {
        if (args.length != 2) {
            throw new ArrayIndexOutOfBoundsException("Invalid number of parameters entered. Enter two options.");
        }
        File file = new File(args[0]);
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder address entered. Enter the search folder address.");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("An invalid search parameter was entered. "
                    + "Enter a search parameter starting with \".\" followed by the desired file extension.");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    static class SearchFiles implements FileVisitor<Path> {

        Predicate<Path> predicate;
        List<Path> paths = new ArrayList<>();

        public SearchFiles(Predicate<Path> predicate) {
            this.predicate = predicate;
        }

        List<Path> getPaths() {
            return paths;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (predicate.test(file)) {
                paths.add(file.toAbsolutePath());
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }
    }
}
