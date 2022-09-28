package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.formatter.DateTimeFormatter;
import ru.job4j.design.srp.formatter.SimpleDataTimeFormatter;
import ru.job4j.design.srp.store.MemStore;
import ru.job4j.design.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportToXml implements Report {

    public static final String HEAD_TEXT = "Name; Hired; Fired; Salary;" + SEPARATOR;
    private Store store;
    private DateTimeFormatter date;

    public ReportToXml(Store store, DateTimeFormatter date) {
        this.store = store;
        this.date = date;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        StringBuilder text = new StringBuilder();
        text.append(HEAD_TEXT);
        for (Employee emp : store.findBy(filter)) {
            text.append(emp.getName()).append(";")
                    .append(date.format(emp.getHired().getTime())).append(";")
                    .append(date.format(emp.getFired().getTime())).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(SEPARATOR);
        }
        JAXBContext context = JAXBContext.newInstance(Employee.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(text, writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static void main(String[] args) throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        DateTimeFormatter date = new SimpleDataTimeFormatter();
        ReportToXml engine = new ReportToXml(store, date);
        System.out.println(engine.generate(em -> true));
    }
}
