package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private List<Food> foodsInWarehouse = new ArrayList<>();
    private final int controlPercent = 25;

    @Override
    public void add(Food food) {
        if (canPutInStore(food)) {
            foodsInWarehouse.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foodsInWarehouse;
    }

    @Override
    public boolean canPutInStore(Food food) {
        return percent(food) < controlPercent;
    }
}
