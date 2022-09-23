package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class SimpleParking implements Parking {

    private final int passCarPlaces;
    private final int truckPlaces;
    private final List<PassCar> passCars;
    private final List<PassCar> trucks;

    public SimpleParking(int passCarPlaces, int truckPlaces) {
        this.passCarPlaces = passCarPlaces;
        this.truckPlaces = truckPlaces;
        passCars = new ArrayList<>(passCarPlaces);
        trucks = new ArrayList<>(truckPlaces);
    }

    @Override
    public boolean add(Car car) {
        boolean result = false;
        return result;
    }
}
