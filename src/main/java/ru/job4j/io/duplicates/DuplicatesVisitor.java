package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    FileProperty testFile = new FileProperty(46, "text.txt");

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file.toFile().getName().equals(testFile.getName()) && file.toFile().length() == testFile.getSize()) {
            System.out.println(file.toAbsolutePath());
        }
        return FileVisitResult.CONTINUE;
    }
}
