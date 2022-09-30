package ru.job4j.design.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.function.Predicate;

public class ReportToJson implements Report {

    private Store store;
    private Gson gson;

    public ReportToJson(Store store) {
        this.store = store;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}

