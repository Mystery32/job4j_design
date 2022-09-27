package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;

import java.util.function.Predicate;

public interface Report {

    String SEPARATOR = System.lineSeparator();

    String generate(Predicate<Employee> filter);
}
