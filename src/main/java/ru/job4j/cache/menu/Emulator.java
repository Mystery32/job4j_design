package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Emulator extends AbstractCache<String, String> {

    public static final String CACHING_DIR = "src/main/java/ru/job4j/cache/files/cache.txt";

    @Override
    protected String load(String key) {
        StringJoiner out = new StringJoiner(" ");
        try (Stream<String> stream = Files.lines(Paths.get(key))) {
            stream.forEach(out::add);
            return out.toString();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    protected void download() {
        try (FileOutputStream out = new FileOutputStream(CACHING_DIR)) {
             out.write(load("src/main/java/ru/job4j/cache/files/names.txt").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
