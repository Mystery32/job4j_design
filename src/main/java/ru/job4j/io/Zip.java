package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path: sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args) {
        boolean haveDirectory = false;
        boolean haveExtensionOfFile = false;
        boolean haveNameOfArchive = false;
        for (String s : args) {
            if (s.contains("d=")) {
                haveDirectory = true;
            }
            if (s.contains("e=")) {
                haveExtensionOfFile = true;
            }
            if (s.contains("o=")) {
                haveNameOfArchive = true;
            }
        }
        if (!haveDirectory) {
            throw new IllegalArgumentException("Backup folder not specified!");
        }
        if (!haveExtensionOfFile) {
            throw  new IllegalArgumentException("The extension of the files to be excluded during archiving is not specified!");
        }
        if (!haveNameOfArchive) {
            throw new IllegalArgumentException("The name of the new archive folder was not specified!");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validate(args);
        ArgsName an = ArgsName.of(args);
        List<Path> list = Search.search(Path.of(an.get("d")), fileExt -> !fileExt.endsWith(an.get("e")));
        zip.packFiles(list, new File(an.get("o")));

    }
}
