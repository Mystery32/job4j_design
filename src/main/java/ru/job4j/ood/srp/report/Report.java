package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.Employee;

import java.util.function.Predicate;

public interface Report {

    String SEPARATOR = System.lineSeparator();

    String generate(Predicate<Employee> filter);
}

