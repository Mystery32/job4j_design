package ru.job4j.ood.lsp.storefood;

import java.util.Calendar;

public class Meat extends Food {

    Meat(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
