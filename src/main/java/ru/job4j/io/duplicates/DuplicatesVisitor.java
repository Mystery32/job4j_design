package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, Path> allFiles = new HashMap<>();
    List<String> duplicatePath = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tempFile = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (allFiles.containsKey(tempFile)) {
            duplicatePath.add(file.toAbsolutePath().toString());
        } else {
            allFiles.put(tempFile, file);
        }
        return super.visitFile(file, attrs);
    }

    public void getDuplicate() {
        for (String s: duplicatePath) {
            System.out.println(s);
        }
    }
}
