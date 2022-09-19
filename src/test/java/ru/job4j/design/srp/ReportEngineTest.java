package ru.job4j.design.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportEngineTest {

    @Test
    public void whenReportedToHtml() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary() * 2).append(";").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenDoublingSalary() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary() * 2).append(";").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>");
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
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html lang=\"en\">").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">").append(System.lineSeparator())
                .append("<title>Report</title>").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator())
                .append("Name; Hired; Fired; Salary;").append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary() * 2).append(";").append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary() * 2).append(";").append(System.lineSeparator())
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary() * 2).append(";").append(System.lineSeparator())
                .append("</body>").append(System.lineSeparator())
                .append("</html>");
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }
}