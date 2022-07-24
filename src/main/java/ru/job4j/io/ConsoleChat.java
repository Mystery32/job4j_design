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
        List<String> phrases = readPhrases();
        List<String> log = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        var random = new Random();
        String phrase = "null";
        boolean active = true;
        System.out.println("Введите фразу:");
        while (!OUT.equals(phrase)) {
            phrase = input.nextLine();
            log.add("Я: " + phrase);
            if (STOP.equals(phrase)) {
                active = false;
                continue;
            }
            if (CONTINUE.equals(phrase)) {
                active = true;
                System.out.println("ПРОДОЛЖАЕМ!");
                log.add("Бот: " + "ПРОДОЛЖАЕМ!");
            }
            if (active) {
                var botPhrase = phrases.get(random.nextInt(phrases.size() - 1));
                log.add("Бот: " + botPhrase);
                System.out.println(botPhrase);
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
