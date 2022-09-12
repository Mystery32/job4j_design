package ru.job4j.gc.leak;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommentGenerator implements Generate {

    public static final String PATH_PHRASES = "src/main/java/ru/job4j/gc/leak/files/phrases.txt";

    private List<Comment> comments = new ArrayList<>();

    public static final int COUNT = 50;

    private List<String> phrases;

    private UserGenerator userGenerator;

    private Random random;

    public CommentGenerator(Random random, UserGenerator userGenerator) {
        this.userGenerator = userGenerator;
        this.random = random;
        read();
    }

    private void read() {
        phrases = read(PATH_PHRASES);
    }

    public List<Comment> getComments() {
        return comments;
    }

    @Override
    public void generate() {
        comments.clear();
        List<Integer> ints = new ArrayList<>();
        random.ints(0, phrases.size())
                .distinct().limit(3).forEach(ints::add);
        for (int i = 0; i < COUNT; i++) {
            String comment = String.format("%s%n%s%n%s", phrases.get(ints.get(0)),
                    phrases.get(ints.get(1)), phrases.get(ints.get(2)));
            comments.add(new Comment(comment,
                    userGenerator.randomUser()));
        }
    }
}
