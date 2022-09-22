package ru.job4j.design.lsp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenFoodForWarehouse() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.OCTOBER, 10);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 18);
        Food meat = new Meat("Стейк", expiryDate, createDate, 500.0, 30);
        Warehouse warehouse = new Warehouse();
        ControlQuality cq = new ControlQuality(List.of(new Trash(), warehouse, new Shop()));
        cq.addToStore(meat);
        assertThat(warehouse.getFoods().get(0)).isEqualTo(meat);
    }

    @Test
    void whenFoodForTrash() {
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 20);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 7);
        Food cake = new Dessert("Пирожное", expiryDate, createDate, 100.0, 20);
        Trash trash = new Trash();
        ControlQuality cq = new ControlQuality(List.of(trash, new Warehouse(), new Shop()));
        cq.addToStore(cake);
        assertThat(trash.getFoods().get(0)).isEqualTo(cake);
    }

    @Test
    void whenFoodForShop() {
        Calendar expiryDateChips = Calendar.getInstance();
        expiryDateChips.set(2022, Calendar.NOVEMBER, 20);
        Calendar createDateChips = Calendar.getInstance();
        createDateChips.set(2022, Calendar.AUGUST, 7);
        Food chips = new Snacks("Московская картошка", expiryDateChips, createDateChips, 50.0, 40);
        Calendar expiryDateNuts = Calendar.getInstance();
        expiryDateNuts.set(2022, Calendar.OCTOBER, 1);
        Calendar createDateNuts = Calendar.getInstance();
        createDateNuts.set(2022, Calendar.AUGUST, 15);
        Food nuts = new Snacks("Соленый арахис", expiryDateNuts, createDateNuts, 80.0, 25);
        Shop shop = new Shop();
        ControlQuality cq = new ControlQuality(List.of(new Trash(), new Warehouse(), shop));
        cq.addToStore(chips);
        cq.addToStore(nuts);
        assertThat(shop.getFoods().get(0)).isEqualTo(chips);
        assertThat(shop.getFoods().get(1)).isEqualTo(nuts);
        assertThat(chips.price).isEqualTo(50);
        assertThat(nuts.price).isEqualTo(60);
    }
}