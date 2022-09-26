package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleParkingTest {

    @Test
    void whenAddTwoPassCarAndOneTruck() {
        Car passCar1 = new PassCar();
        Car passCar2 = new PassCar();
        Car truck = new Truck(2);
        Parking parking = new SimpleParking(2, 1);
        assertThat(parking.add(passCar1)).isTrue();
        assertThat(parking.add(passCar2)).isTrue();
        assertThat(parking.add(truck)).isTrue();
    }

    @Test
    void whenAddTwoTrucks() {
        Car truck1 = new Truck(4);
        Car truck2 = new Truck(2);
        Parking parking = new SimpleParking(2, 1);
        assertThat(parking.add(truck1)).isTrue();
        assertThat(parking.add(truck2)).isTrue();
    }

    @Test
    void whenAddExtraPassCars() {
        Car passCar1 = new PassCar();
        Car passCar2 = new PassCar();
        Car passCar3 = new PassCar();
        Car truck = new Truck(4);
        Parking parking = new SimpleParking(2, 1);
        assertThat(parking.add(truck)).isTrue();
        assertThat(parking.add(passCar1)).isTrue();
        assertThat(parking.add(passCar2)).isTrue();
        assertThat(parking.add(passCar3)).isFalse();
    }

    @Test
    void whenNoPlaceForTrucks() {
        Car passCar = new PassCar();
        Car truck1 = new Truck(2);
        Car truck2 = new Truck(2);
        Parking parking = new SimpleParking(2, 1);
        assertThat(parking.add(passCar)).isTrue();
        assertThat(parking.add(truck1)).isTrue();
        assertThat(parking.add(truck2)).isFalse();
    }

    @Test
    void whenParkingPassCarOnTruckPlace() {
        Car passCar1 = new PassCar();
        Car passCar2 = new PassCar();
        Parking parking = new SimpleParking(1, 1);
        assertThat(parking.add(passCar1)).isTrue();
        assertThat(parking.add(passCar2)).isFalse();
    }
}