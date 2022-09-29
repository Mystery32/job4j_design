package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.design.srp.currency.Currency;
import ru.job4j.design.srp.currency.CurrencyConverter;
import ru.job4j.design.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.design.srp.formatter.DateTimeFormatter;
import ru.job4j.design.srp.formatter.SimpleDataTimeFormatter;
import ru.job4j.design.srp.report.*;
import ru.job4j.design.srp.store.MemStore;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Comparator;

import static org.assertj.core.api.Assertions.*;
import static ru.job4j.design.srp.report.ReportEngine.DATE_FORMAT;

class ReportEngineTest {

    public static final String SEPARATOR = System.lineSeparator();
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
        ReportEngine engine = new ReportEngine(store);
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
        DateTimeFormatter date = new SimpleDataTimeFormatter();
        ReportForIT engine = new ReportForIT(store, date);
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
        DateTimeFormatter date = new SimpleDataTimeFormatter();
        CurrencyConverter converter = new InMemoryCurrencyConverter();
        ReportForAccounting engine = new ReportForAccounting(store, date, converter);
        StringBuilder expect = new StringBuilder()
                .append(HEAD_TEXT)
                .append(worker.getName()).append(";")
                .append(date.format(worker.getHired().getTime())).append(";")
                .append(date.format(worker.getFired().getTime())).append(";")
                .append(converter.convert(Currency.RUB, worker.getSalary(), Currency.USD)).append(";");
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
        ReportForHR engine = new ReportForHR(store, comparator);
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

    @Test
    public void whenReportToJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        ReportToJson engine = new ReportToJson(store, gson);
        String template = """
                [
                  {
                    "name": "%1$s",
                    "hired": {
                      "year": %2$s,
                      "month": %3$s,
                      "dayOfMonth": %4$s,
                      "hourOfDay": %5$s,
                      "minute": %6$s,
                      "second": %7$s
                    },
                    "fired": {
                      "year": %2$s,
                      "month": %3$s,
                      "dayOfMonth": %4$s,
                      "hourOfDay": %5$s,
                      "minute": %6$s,
                      "second": %7$s
                    },
                    "salary": %8$s
                  }
                ]""".formatted(worker.getName(),
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH),
                now.get(Calendar.HOUR_OF_DAY),
                now.get(Calendar.MINUTE),
                now.get(Calendar.SECOND),
                worker.getSalary());
        assertThat(engine.generate(em -> true)).isEqualTo(template);
    }

    @Test
    public void whenReportToXml() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        OffsetDateTime date = OffsetDateTime.ofInstant(now.toInstant(), now.getTimeZone().toZoneId());
        JAXBContext context = JAXBContext.newInstance(ReportToXml.Employees.class);
        Marshaller marshaller = context.createMarshaller();
        ReportToXml engine = new ReportToXml(store, context, marshaller);
        String template = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <employees>
                    <employee>
                        <name>%s</name>
                        <hired>%s</hired>
                        <fired>%s</fired>
                        <salary>%s</salary>
                    </employee>
                </employees>
                """.formatted(worker.getName(),
                date,
                date,
                worker.getSalary());
        assertThat(engine.generate(em -> true)).isEqualTo(template);
    }
}