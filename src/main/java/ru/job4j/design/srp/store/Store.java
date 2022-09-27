package ru.job4j.design.srp.store;

import ru.job4j.design.srp.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Employee em);

    List<Employee> findBy(Predicate<Employee> filter);
}