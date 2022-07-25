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

    private void validate(String[] args, ArgsName argsName) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Invalid number of parameters entered. Enter 3 parameters!");
        }
        if (!Path.of(argsName.get("d")).toFile().isDirectory()) {
            throw new IllegalArgumentException("Incorrect folder for archiving!");
        }
        if (!argsName.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Incorrectly specified extension of files that should be excluded during archiving!");
        }
        if (!argsName.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Archive name is incorrect!");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName an = ArgsName.of(args);
        zip.validate(args, an);
        List<Path> list = Search.search(Path.of(an.get("d")), fileExt -> !fileExt.toString().endsWith(an.get("e")));
        zip.packFiles(list, new File(an.get("o")));

    }
}