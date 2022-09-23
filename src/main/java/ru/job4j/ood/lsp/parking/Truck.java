package ru.job4j.ood.lsp.parking;

public class Truck extends Car {

    public Truck(int size) {
        super(size);
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Размер грузовика указан некорректно!");
        }

    }
}
