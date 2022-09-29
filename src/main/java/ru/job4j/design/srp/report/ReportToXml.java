package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXml implements Report {

    private Store store;
    private JAXBContext context;
    private Marshaller marshaller;

    public ReportToXml(Store store, JAXBContext context, Marshaller marshaller) {
        this.store = store;
        this.context = context;
        this.marshaller = marshaller;
    }

    @XmlRootElement
    public static class Employees {
        @XmlElement(name = "employee")

        private List<Employee> employees;

        public Employees() { }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        var employees = store.findBy(filter);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }
}

