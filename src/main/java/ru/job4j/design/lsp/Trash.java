package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {

    private final List<Food> foodsInTrash = new ArrayList<>();
    private final int controlPercent = 100;

    @Override
    public void add(Food food) {
        if (canPutInStore(food)) {
            foodsInTrash.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foodsInTrash;
    }

    @Override
    public boolean canPutInStore(Food food) {
        return percent(food) == controlPercent;
    }
}
