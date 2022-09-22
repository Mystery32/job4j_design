package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {

    private final List<Food> foodsInShop = new ArrayList<>();
    private static final int PERCENT_25 = 25;
    private static final int PERCENT_75 = 75;
    private static final int PERCENT_100 = 100;

    @Override
    public boolean add(Food food) {
        boolean result = false;
        if (canPutInStore(food)) {
            if (checkDiscount(food)) {
                food.setPrice(food.price - food.price * food.discount / 100);
            }
            foodsInShop.add(food);
            result = true;
        }
        return result;
    }

    @Override
    public List<Food> getFoods() {
        return List.copyOf(foodsInShop);
    }

    @Override
    public boolean canPutInStore(Food food) {
        return (percent(food) >= PERCENT_25 && percent(food) < PERCENT_100);
    }

    public boolean checkDiscount(Food food) {
        return (percent(food) >= PERCENT_75 && percent(food) < PERCENT_100);
    }
}
