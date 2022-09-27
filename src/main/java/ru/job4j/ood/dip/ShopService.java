package ru.job4j.ood.dip;

import java.util.ArrayList;
import java.util.List;

public class ShopService implements Store {

    private List<Products> store = new ArrayList<>();

    @Override
    public void add(Products product) {
        if (product == null) {
            System.out.println("Get error with" + product);
            throw new IllegalArgumentException("Product is out of stock!");
        }
        store.add(product);
    }

    public void sellProducts(Products product) {
        if (product == null) {
            System.out.println("Get error with" + product);
            throw new IllegalArgumentException("Product is out of stock!");
        }
        store.remove(product);
    }
}
