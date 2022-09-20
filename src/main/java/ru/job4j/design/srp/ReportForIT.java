package ru.job4j.design.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ReportForIT implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");
    public static final String SEPARATOR = System.lineSeparator();
    private Store store;

    public ReportForIT(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<!DOCTYPE html>")
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator());
        for (Employee emp : store.findBy(filter)) {
            text.append(emp.getName()).append(";")
                    .append(DATE_FORMAT.format(emp.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        text.append("</body>").append(System.lineSeparator())
                .append("</html>");
        return text.toString();
    }
}
