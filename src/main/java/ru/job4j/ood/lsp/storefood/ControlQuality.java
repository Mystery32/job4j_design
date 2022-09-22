package ru.job4j.ood.lsp.storefood;

import java.util.List;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void addToStore(Food food) {
        for (Store s: stores) {
            s.add(food);
        }
    }
}

