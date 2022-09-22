package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportForIT implements Report {

    public static final String HTML_TEXT_START = "<!DOCTYPE html>" + SEPARATOR + "<html lang=\"en\">" + SEPARATOR
            + "<head>" + SEPARATOR + "<meta charset=\"UTF-8\">" + SEPARATOR + "<title>Report</title>"
            + SEPARATOR + "</head>" + SEPARATOR + "<body>" + SEPARATOR + "Name; Hired; Fired; Salary;"
            + SEPARATOR;
    public static final String HTML_TEXT_END = "</body>" + SEPARATOR + "</html>";

    private Store store;
    private DateTimeFormatter date;

    public ReportForIT(Store store, DateTimeFormatter date) {
        this.store = store;
        this.date = date;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HTML_TEXT_START);
        for (Employee emp : store.findBy(filter)) {
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        text.append(HTML_TEXT_END);
        return text.toString();
    }
}
