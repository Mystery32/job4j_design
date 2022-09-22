package ru.job4j.ood.lsp.storefood;

import java.util.Calendar;
import java.util.List;

public interface Store {

    void add(Food food);

    List<Food> getFoods();

    default int percent(Food food) {
        double dayPassed = Calendar.getInstance().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        double shelfLife = food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis();
        int percent = (int) (dayPassed / shelfLife * 100);
        if (percent > 100) {
            percent = 100;
        }
        return percent;
    }

    boolean canPutInStore(Food food);
}
