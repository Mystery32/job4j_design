package ru.job4j.ood.lsp.parking;

import static ru.job4j.ood.lsp.parking.PassCar.CAR_SIZE;

public class Truck extends Car {

    public Truck(int size) {
        super(size);
        if (size <= CAR_SIZE) {
            throw new IllegalArgumentException("Размер грузовика указан некорректно!");
        }

    }
}
