package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class ReportToJson implements Report {

    private final Store store;
    private final Gson gson;

    public ReportToJson(Store store) {
        this.store = store;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}

