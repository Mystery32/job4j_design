package ru.job4j.design.lsp;

import java.util.Calendar;

public class Snacks extends Food {

    Snacks(String name, Calendar expiryDate, Calendar createDate, double price, double discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
