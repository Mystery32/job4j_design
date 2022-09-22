package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.ReportEngine.DATE_FORMAT;

class ReportEngineTest {

    public static final String SEPARATOR = System.lineSeparator();
    public static final int DOUBLE_SALARY = 2;
    public static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    public static final String HTML_TEXT_START = "<!DOCTYPE html>" + SEPARATOR + "<html lang=\"en\">" + SEPARATOR
            + "<head>" + SEPARATOR + "<meta charset=\"UTF-8\">" + SEPARATOR + "<title>Report</title>"
            + SEPARATOR + "</head>" + SEPARATOR + "<body>" + SEPARATOR + "Name; Hired; Fired; Salary;"
            + SEPARATOR;
    public static final String HTML_TEXT_END = "</body>" + SEPARATOR + "</html>";

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append(HEAD_TEXT)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenReportedToHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateTimeFormatter date = new SimpleData();
        Report engine = new ReportForIT(store, date);
        StringBuilder expect = new StringBuilder()
                .append(HTML_TEXT_START)
                .append(worker.getName()).append(";")
                .append(date.format(worker.getHired().getTime())).append(";")
                .append(date.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";").append(SEPARATOR)
                .append(HTML_TEXT_END);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenReportForAccounting() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateTimeFormatter date = new SimpleData();
        Report engine = new ReportForAccounting(store, date);
        StringBuilder expect = new StringBuilder()
                .append(HEAD_TEXT)
                .append(worker.getName()).append(";")
                .append(date.format(worker.getHired().getTime())).append(";")
                .append(date.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary() * DOUBLE_SALARY).append(";");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenRequestFromHR() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Artem", now, now, 120);
        Employee worker3 = new Employee("Viktor", now, now, 70);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        Comparator<Employee> comparator = (o1, o2) -> (int) (o1.getSalary() - o2.getSalary());
        Report engine = new ReportForHR(store, comparator);
        StringBuilder expect = new StringBuilder()
                .append(HEAD_TEXT)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";").append(SEPARATOR)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";").append(SEPARATOR)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";").append(SEPARATOR);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}