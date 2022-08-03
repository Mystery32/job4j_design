package ru.job4j.io.task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Searcher {

    private final String log;

    public Searcher(String log) {
        this.log = log;
    }

    private void validate(String[] args, ArgsName argsName) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of parameters entered. Enter 4 parameters!");
        }
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Search folder is incorrect. Enter an existing folder!");
        }
        if (!argsName.get("t").contains("mask")) {
            if (!argsName.get("t").contains("name")) {
                if (!argsName.get("t").contains("regex")) {
                    throw new IllegalArgumentException("Search type specified incorrectly. Enter 'mask', 'name', or 'regex'!");
                }
            }
        }
        if (!argsName.get("o").endsWith(".txt")) {
            throw new IllegalArgumentException("File name for writing data is not correct. Enter a filename with .txt extension!");
        }
    }
    
    public static Predicate<Path> filter(ArgsName argsName) {
        Predicate<Path> paths = null;
        String typeSearch = argsName.get("t");
        String paramOfSearch = argsName.get("n");
        if ("name".equals(typeSearch)) {
            paths = p -> p.toFile().getName().equals(paramOfSearch);
        } else if ("mask".equals(typeSearch)) {
            String mask = paramOfSearch
                    .replace("*", "\\w")
                    .replace("?", "\\w");
            paths = p -> Pattern.compile(mask)
                    .matcher(p.toString())
                    .find();
        } else if ("regex".equals(typeSearch)) {
            paths = p -> Pattern.compile(paramOfSearch)
                    .matcher(p.toString())
                    .find();
        }
        return paths;
    }

    public static List<Path> search(Path root, ArgsName argsName) throws IOException {
        Predicate<Path> condition = filter(argsName);
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    static class SearchFiles implements FileVisitor<Path> {

        private Predicate<Path> predicate;
        private List<Path> paths = new ArrayList<>();

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

    private void saveLog(List<Path> paths) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(log, StandardCharsets.UTF_8))) {
            paths.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(String[] args) throws IOException {
        ArgsName an = ArgsName.of(args);
        validate(args, an);
        List<Path> list = search(Path.of(an.get("d")), an);
        saveLog(list);
    }

    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher("log.txt");
        searcher.run(args);
    }
}
