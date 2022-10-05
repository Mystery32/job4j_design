package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.store.Store;
import ru.job4j.ood.srp.formatter.DateTimeFormatter;

import java.util.function.Predicate;

public class ReportForIT implements Report {

    private static final String HTML_TEXT = """
            <!DOCTYPE html>
            <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <title>Report</title>
                </head>
                <body>
                    Name; Hired; Fired; Salary;
                    %s
                </body>
            </html>
            """;

    private final Store store;
    private final DateTimeFormatter date;

    public ReportForIT(Store store, DateTimeFormatter date) {
        this.store = store;
        this.date = date;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee emp : store.findBy(filter)) {
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";");
        }
        return String.format(HTML_TEXT, text);
    }
}
