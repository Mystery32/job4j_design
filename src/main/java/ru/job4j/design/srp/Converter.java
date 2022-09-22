package ru.job4j.design.srp;

import java.util.Currency;

public class Converter implements CurrencyConverter {

    @Override
    public double convert(Currency source, double sourceValue, Currency target) {
        return sourceValue;
    }
}
