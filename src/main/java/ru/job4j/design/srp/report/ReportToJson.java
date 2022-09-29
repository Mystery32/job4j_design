package ru.job4j.design.srp.report;

import com.google.gson.Gson;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.function.Predicate;

public class ReportToJson implements Report {

    private Store store;
    private Gson gson;

    public ReportToJson(Store store, Gson gson) {
        this.store = store;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}

