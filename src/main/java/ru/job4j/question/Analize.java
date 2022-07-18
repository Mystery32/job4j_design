package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> prev = new HashMap<>();
        Map<Integer, String> cur = new HashMap<>();
        for (User pr: previous) {
            prev.put(pr.getId(), pr.getName());
        }
        for (User cu: current) {
            cur.put(cu.getId(), cu.getName());
        }

        for (Integer key: cur.keySet()) {
            if (!prev.containsKey(key)) {
                added++;
            }
        }
        for (Integer key: prev.keySet()) {
            if (!cur.containsKey(key)) {
                deleted++;
            } else if (!prev.get(key).equals(cur.get(key))) {
                changed++;
            }
        }
        return new Info(added, changed, deleted);
    }
}
