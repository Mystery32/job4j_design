package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class ReportToXml implements Report {

    private final Store store;
    private final JAXBContext context;
    private final Marshaller marshaller;

    public ReportToXml(Store store) throws JAXBException {
        this.store = store;
        context = JAXBContext.newInstance(ReportToXml.Employees.class);
        marshaller = context.createMarshaller();
    }

    @XmlRootElement
    public static class Employees {
        @XmlElement(name = "employee")

        private List<Employee> employees;

        public Employees() {
        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        var employees = store.findBy(filter);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(new Employees(employees), writer);
            xml = writer.getBuffer().toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return xml;
    }
}

