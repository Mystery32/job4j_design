package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.store.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHR implements Report {

    public static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    private Comparator<Employee> comparator;
    private Store store;

    public ReportForHR(Store store, Comparator<Employee> comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employee = store.findBy(filter);
        employee.sort(comparator.reversed());
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee emp : employee) {
            text.append(emp.getName()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
