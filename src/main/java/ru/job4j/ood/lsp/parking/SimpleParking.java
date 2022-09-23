package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private final List<PassCar> passCars = new ArrayList<>();
    private final List<Truck> trucks = new ArrayList<>();
    private final int passCarPlaces;
    private final int truckPlaces;

    public SimpleParking(int passCarPlaces, int truckPlaces) {
        this.passCarPlaces = passCarPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean add(Car car) {
        return false;
    }
}
