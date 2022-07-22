package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    Map<FileProperty, List<Path>> allFiles = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty tempFile = new FileProperty(file.toFile().length(), file.toFile().getName());
        allFiles.putIfAbsent(tempFile, new ArrayList<>());
        allFiles.get(tempFile).add(file);
        return super.visitFile(file, attrs);
    }

    public void getDuplicate() {
        for (FileProperty fp: allFiles.keySet()) {
            if (allFiles.get(fp).size() > 1) {
                for (Path p: allFiles.get(fp)) {
                    System.out.println(p.toAbsolutePath());
                }
            }
        }
    }
}
