package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private static final int PARKING_SIZE = 100;
    private final List<Car> parkingCars = new ArrayList<>();

    @Override
    public void checkCanParking(List<Car> cars) {
        int placesLeft = PARKING_SIZE;
        for (Car car: cars) {
            if (placesLeft >= car.size) {
                parkingCars.add(car);
                placesLeft -= car.size;
            } else {
                System.out.println("All parking spaces are occupied!");
            }
        }
    }
}
