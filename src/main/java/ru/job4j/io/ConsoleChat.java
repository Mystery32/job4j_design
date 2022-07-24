package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String phrase = "null";
        boolean active = true;
        System.out.println("Введите фразу:");
        while (!phrase.equals(OUT)) {
            phrase = input.nextLine();
            if (phrase.equals(STOP)) {
                active = false;
                log.add("Я: " + phrase);
                continue;
            }
            if (phrase.equals(CONTINUE)) {
                active = true;
                log.add("Я: " + phrase);
                System.out.println("ПРОДОЛЖАЕМ!");
                log.add("Бот: " + "ПРОДОЛЖАЕМ!");
            } else {
                log.add("Я: " + phrase);
                if (!phrase.equals(OUT) && active) {
                    log.add("Бот: " + randomPhrases());
                }
            }
            saveLog(log);
        }
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private String randomPhrases() {
        String botPhrase = null;
        Map<String, String> map = new HashMap<>();
        String[] array;
        for (String s : readPhrases()) {
            array = s.split("=", 2);
            map.put(array[0], array[1]);
        }
        int randomNumber = new Random().nextInt(6);
        if (randomNumber == 0) {
            botPhrase = map.get("0");
            System.out.println(map.get("0"));
        }
        if (randomNumber == 1) {
            botPhrase = map.get("1");
            System.out.println(map.get("1"));
        }
        if (randomNumber == 2) {
            botPhrase = map.get("2");
            System.out.println(map.get("2"));
        }
        if (randomNumber == 3) {
            botPhrase = map.get("3");
            System.out.println(map.get("3"));
        }
        if (randomNumber == 4) {
            botPhrase = map.get("4");
            System.out.println(map.get("4"));
        }
        if (randomNumber == 5) {
            botPhrase = map.get("5");
            System.out.println(map.get("5"));
        }
        return botPhrase;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("dialog.txt", "bot_phrases.txt");
        cc.run();
    }
}
