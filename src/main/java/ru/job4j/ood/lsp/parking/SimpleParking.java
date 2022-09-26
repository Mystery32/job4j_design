package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

import static ru.job4j.ood.lsp.parking.PassCar.CAR_SIZE;

public class SimpleParking implements Parking {

    private final int passCarPlaces;
    private final int truckPlaces;
    private final List<Car> passCars;
    private final List<Car> trucks;

    public SimpleParking(int passCarPlaces, int truckPlaces) {
        this.passCarPlaces = passCarPlaces;
        this.truckPlaces = truckPlaces;
        passCars = new ArrayList<>(passCarPlaces);
        trucks = new ArrayList<>(truckPlaces);
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        if (car.size <= CAR_SIZE && passCars.size() < passCarPlaces) {
            passCars.add(car);
            result = true;
        } else if (car.size > CAR_SIZE && trucks.size() < truckPlaces) {
            trucks.add(car);
            result = true;
        } else if (car.size <= passCarPlaces - passCars.size()) {
            passCars.add(car);
            result = true;
        }
        return result;
    }
}

