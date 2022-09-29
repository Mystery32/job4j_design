package ru.job4j.ood.lsp.storefood;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addToStore(Food food) {
        for (Store s: stores) {
            if (s.add(food)) {
                break;
            }
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store s: stores) {
            foods.addAll(s.getFoods());
            s.getFoods().clear();
        }
        for (Food f: foods) {
            addToStore(f);
        }
    }
}

