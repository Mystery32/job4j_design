package ru.job4j.design.srp;

import java.util.Currency;

public interface CurrencyConverter {

    double convert(Currency source, double sourceValue, Currency target);
}
