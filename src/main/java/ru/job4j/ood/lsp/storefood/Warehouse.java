package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {

    private static final int PERCENT_25 = 25;
    private List<Food> foodsInWarehouse = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (canPutInStore(food)) {
            foodsInWarehouse.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(foodsInWarehouse);
    }

    @Override
    public boolean canPutInStore(Food food) {
        return percent(food) < PERCENT_25;
    }
}
