package ru.job4j.design.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.formatter.DateTimeFormatter;
import ru.job4j.design.srp.store.Store;

import java.util.function.Predicate;

public class ReportToJson implements Report {

    public static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    private Store store;
    private DateTimeFormatter date;

    public ReportToJson(Store store, DateTimeFormatter date) {
        this.store = store;
        this.date = date;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee emp : store.findBy(filter)) {
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        var lib = new GsonBuilder().create();
        return lib.toJson(text);
    }
}
