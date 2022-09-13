package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;
import java.io.IOException;
import java.util.Scanner;

public class Emulator {

    private static final String MENU = """
                Введите 1, чтобы указать кэшируемую директорию.
                Введите 2, чтобы загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;
    private static final String SELECT = "Выберите меню";
    private static final int FIRST = 1;
    private static final int SECOND = 2;
    private static final int THIRD = 3;
    private static final String TEXT_OF_DIR = "Укажите кэшируемую директорию";
    private static final String TEXT_OF_FROM_FILE = "Укажите имя файла, из которого будут получены данные";
    private static final String TEXT_OF_IN_FILE = "Укажите имя кэш-файла";
    private static final String EXIT = "Конец работы";
    private static DirFileCache dirFileCache;

    private static void start(Scanner scanner) throws IOException {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (FIRST == userChoice) {
                System.out.println(TEXT_OF_DIR);
                String cachingDir = scanner.nextLine();
                dirFileCache = new DirFileCache(cachingDir);
            } else if (SECOND == userChoice) {
                System.out.println(TEXT_OF_FROM_FILE);
                String textFile = scanner.nextLine();
                System.out.println(TEXT_OF_IN_FILE);
                String cachingFile = scanner.nextLine();
                dirFileCache.put(cachingFile, dirFileCache.get(textFile));
            } else if (THIRD == userChoice) {
                System.out.println(TEXT_OF_IN_FILE);
                String cachingFile = scanner.nextLine();
                System.out.println(dirFileCache.get(cachingFile));
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }
}
