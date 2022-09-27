package ru.job4j.design.srp.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDataTimeFormatter implements DateTimeFormatter {

    private static final String FORMAT = "dd:MM:yyyy HH:mm";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT);

    @Override
    public String format(Date date) {
        return DATE_FORMAT.format(date);
    }
}
