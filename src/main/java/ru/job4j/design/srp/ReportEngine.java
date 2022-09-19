package ru.job4j.design.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employee = store.findBy(filter);
        employee.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee emp : employee) {
            emp.setSalary(emp.getSalary() * 2);
            text.append(emp.getName()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        text.append("</body>").append(System.lineSeparator())
                .append("</html>");
        return text.toString();
    }
}
