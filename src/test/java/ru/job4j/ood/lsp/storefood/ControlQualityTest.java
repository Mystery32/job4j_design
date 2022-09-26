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
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) + 10);
        Calendar createDate = Calendar.getInstance();
        createDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 2);
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
        expiryDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH));
        Calendar createDate = Calendar.getInstance();
        createDate.set(expiryDate.get(Calendar.YEAR), expiryDate.get(Calendar.MONTH),
                expiryDate.get(Calendar.DAY_OF_MONTH) - 25);
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
        expiryDateChips.set(expiryDateChips.get(Calendar.YEAR), expiryDateChips.get(Calendar.MONTH),
                expiryDateChips.get(Calendar.DAY_OF_MONTH) + 10);
        Calendar createDateChips = Calendar.getInstance();
        createDateChips.set(createDateChips.get(Calendar.YEAR), createDateChips.get(Calendar.MONTH),
                createDateChips.get(Calendar.DAY_OF_MONTH) - 10);
        Food chips = new Snacks("Московская картошка", expiryDateChips, createDateChips, 50.0, 40);
        Calendar expiryDateNuts = Calendar.getInstance();
        expiryDateNuts.set(expiryDateNuts.get(Calendar.YEAR), expiryDateNuts.get(Calendar.MONTH),
                expiryDateNuts.get(Calendar.DAY_OF_MONTH) + 5);
        Calendar createDateNuts = Calendar.getInstance();
        createDateNuts.set(createDateNuts.get(Calendar.YEAR), createDateNuts.get(Calendar.MONTH),
                createDateNuts.get(Calendar.DAY_OF_MONTH) - 20);
        Food nuts = new Snacks("Соленый арахис", expiryDateNuts, createDateNuts, 80.0, 25);
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(chips);
        cq.addToStore(nuts);
        assertThat(shop.getFoods().containsAll(List.of(chips, nuts))).isTrue();
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
        expiryDateMeat.set(expiryDateMeat.get(Calendar.YEAR), expiryDateMeat.get(Calendar.MONTH),
                expiryDateMeat.get(Calendar.DAY_OF_MONTH) + 10);
        Calendar createDateMeat = Calendar.getInstance();
        createDateMeat.set(createDateMeat.get(Calendar.YEAR), createDateMeat.get(Calendar.MONTH),
                createDateMeat.get(Calendar.DAY_OF_MONTH) - 2);
        Food meat = new Meat("Стейк", expiryDateMeat, createDateMeat, 500.0, 30);
        Calendar expiryDateCake = Calendar.getInstance();
        expiryDateCake.set(expiryDateCake.get(Calendar.YEAR), expiryDateCake.get(Calendar.MONTH),
                expiryDateCake.get(Calendar.DAY_OF_MONTH));
        Calendar createDateCake = Calendar.getInstance();
        createDateCake.set(expiryDateCake.get(Calendar.YEAR), expiryDateCake.get(Calendar.MONTH),
                expiryDateCake.get(Calendar.DAY_OF_MONTH) - 25);
        Food cake = new Dessert("Пирожное", expiryDateCake, createDateCake, 100.0, 20);
        Calendar expiryDateChips = Calendar.getInstance();
        expiryDateChips.set(expiryDateChips.get(Calendar.YEAR), expiryDateChips.get(Calendar.MONTH),
                expiryDateChips.get(Calendar.DAY_OF_MONTH) + 10);
        Calendar createDateChips = Calendar.getInstance();
        createDateChips.set(createDateChips.get(Calendar.YEAR), createDateChips.get(Calendar.MONTH),
                createDateChips.get(Calendar.DAY_OF_MONTH) - 10);
        Food chips = new Snacks("Московская картошка", expiryDateChips, createDateChips, 50.0, 40);
        Calendar expiryDateNuts = Calendar.getInstance();
        expiryDateNuts.set(expiryDateNuts.get(Calendar.YEAR), expiryDateNuts.get(Calendar.MONTH),
                expiryDateNuts.get(Calendar.DAY_OF_MONTH) + 5);
        Calendar createDateNuts = Calendar.getInstance();
        createDateNuts.set(createDateNuts.get(Calendar.YEAR), createDateNuts.get(Calendar.MONTH),
                createDateNuts.get(Calendar.DAY_OF_MONTH) - 20);
        Food nuts = new Snacks("Соленый арахис", expiryDateNuts, createDateNuts, 80.0, 25);
        ControlQuality cq = new ControlQuality(stores);
        cq.addToStore(meat);
        cq.addToStore(cake);
        cq.addToStore(chips);
        cq.addToStore(nuts);
        assertThat(warehouse.getFoods().containsAll(List.of(meat))).isTrue();
        assertThat(trash.getFoods().containsAll(List.of(cake))).isTrue();
        assertThat(shop.getFoods().containsAll(List.of(chips, nuts))).isTrue();
    }
}