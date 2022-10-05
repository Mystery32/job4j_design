package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.store.Store;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    private static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;

    private final Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
