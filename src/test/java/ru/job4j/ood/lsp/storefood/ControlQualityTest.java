package ru.job4j.ood.lsp.storefood;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenFoodForWarehouse() {
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        Shop shop = new Shop();
        List<Store> stores = List.of(warehouse, trash, shop);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.OCTOBER, 10);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 18);
        Food meat = new Meat("Стейк", expiryDate, createDate, 500.0, 30);
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(meat);
        assertThat(warehouse.getFoods().contains(meat)).isTrue();
    }

    @Test
    void whenFoodForTrash() {
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        Shop shop = new Shop();
        List<Store> stores = List.of(warehouse, trash, shop);
        Calendar expiryDate = Calendar.getInstance();
        expiryDate.set(2022, Calendar.SEPTEMBER, 20);
        Calendar createDate = Calendar.getInstance();
        createDate.set(2022, Calendar.SEPTEMBER, 7);
        Food cake = new Dessert("Пирожное", expiryDate, createDate, 100.0, 20);
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(cake);
        assertThat(trash.getFoods().contains(cake)).isTrue();
    }

    @Test
    void whenFoodForShop() {
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        Shop shop = new Shop();
        List<Store> stores = List.of(warehouse, trash, shop);
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
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(chips);
        cq.addToStore(nuts);
        assertThat(shop.getFoods().contains(chips)).isTrue();
        assertThat(shop.getFoods().contains(nuts)).isTrue();
        assertThat(chips.price).isEqualTo(50);
        assertThat(nuts.price).isEqualTo(60);
    }

    @Test
    void whenMultiFoodsForStore() {
        Warehouse warehouse = new Warehouse();
        Trash trash = new Trash();
        Shop shop = new Shop();
        List<Store> stores = List.of(warehouse, trash, shop);
        Calendar expiryDateMeat = Calendar.getInstance();
        expiryDateMeat.set(2022, Calendar.OCTOBER, 10);
        Calendar createDateMeat = Calendar.getInstance();
        createDateMeat.set(2022, Calendar.SEPTEMBER, 18);
        Food meat = new Meat("Стейк", expiryDateMeat, createDateMeat, 500.0, 30);
        Calendar expiryDateCake = Calendar.getInstance();
        expiryDateCake.set(2022, Calendar.SEPTEMBER, 20);
        Calendar createDateCake = Calendar.getInstance();
        createDateCake.set(2022, Calendar.SEPTEMBER, 7);
        Food cake = new Dessert("Пирожное", expiryDateCake, createDateCake, 100.0, 20);
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
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(meat);
        cq.addToStore(cake);
        cq.addToStore(chips);
        cq.addToStore(nuts);
        assertThat(warehouse.getFoods().contains(meat)).isTrue();
        assertThat(trash.getFoods().contains(cake)).isTrue();
        assertThat(shop.getFoods().contains(chips)).isTrue();
        assertThat(shop.getFoods().contains(nuts)).isTrue();

    }
}