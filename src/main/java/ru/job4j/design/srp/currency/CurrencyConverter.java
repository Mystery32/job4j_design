package ru.job4j.design.srp.currency;

public interface CurrencyConverter {

    double convert(Currency source, double sourceValue, Currency target);
}
