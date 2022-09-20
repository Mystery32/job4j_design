package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();
    public static final int DOUBLE_SALARY = 2;
    private Store store;

    public ReportForAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR);
        for (Employee emp : store.findBy(filter)) {
            emp.setSalary(emp.getSalary() * DOUBLE_SALARY);
            text.append(emp.getName()).append(";")
                    .append(DATE_FORMAT.format(emp.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
