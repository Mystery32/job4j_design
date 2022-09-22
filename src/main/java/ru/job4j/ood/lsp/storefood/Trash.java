package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> foodsInTrash = new ArrayList<>();
    private static final int PERCENT_100 = 100;

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (canPutInStore(food)) {
            foodsInTrash.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(foodsInTrash);
    }

    @Override
    public boolean canPutInStore(Food food) {
        return percent(food) == PERCENT_100;
    }
}
