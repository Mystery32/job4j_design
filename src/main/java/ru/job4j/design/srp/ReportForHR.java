package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportForHR implements Report {

    public static final String SEPARATOR = System.lineSeparator();
    private Store store;

    public ReportForHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employee = store.findBy(filter);
        employee.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(SEPARATOR);
        for (Employee emp : employee) {
            text.append(emp.getName()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        return text.toString();
    }
}
