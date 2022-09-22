package ru.job4j.design.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> foodsInShop = new ArrayList<>();
    private final int controlDownPercent = 25;
    private final int controlMiddlePercent = 75;
    private final int controlUpPercent = 100;

    @Override
    public void add(Food food) {
        if (canPutInStore(food)) {
            if (checkDiscount(food)) {
                food.setPrice(food.price - food.price * food.discount / 100);
            }
            foodsInShop.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foodsInShop;
    }

    @Override
    public boolean canPutInStore(Food food) {
        return (percent(food) >= controlDownPercent && percent(food) < controlUpPercent);
    }

    public boolean checkDiscount(Food food) {
        return (percent(food) >= controlMiddlePercent && percent(food) < controlUpPercent);
    }
}
