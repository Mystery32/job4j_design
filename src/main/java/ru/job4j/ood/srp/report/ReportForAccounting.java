package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.store.Store;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeFormatter;

import java.util.function.Predicate;

public class ReportForAccounting implements Report {

    private static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    private final Store store;
    private final DateTimeFormatter date;
    private final CurrencyConverter converter;

    public ReportForAccounting(Store store, DateTimeFormatter date, CurrencyConverter converter) {
        this.store = store;
        this.date = date;
        this.converter = converter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee emp : store.findBy(filter)) {
            emp.setSalary(converter.convert(Currency.RUB, emp.getSalary(), Currency.USD));
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";");
        }
        return text.toString();
   }
}
