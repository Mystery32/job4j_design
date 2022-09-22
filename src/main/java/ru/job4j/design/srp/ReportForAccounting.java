package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    public static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    public static final int DOUBLE_SALARY = 2;
    private Store store;
    private DateTimeFormatter date;
    private CurrencyConverter converter;

    public ReportForAccounting(Store store, DateTimeFormatter date) {
        this.store = store;
        this.date = date;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee emp : store.findBy(filter)) {
            emp.setSalary(emp.getSalary() * DOUBLE_SALARY);
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";");
        }
        return text.toString();
   }
}
