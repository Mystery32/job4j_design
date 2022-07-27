package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int i = 7;
        float f = 5;
        double d = 3;
        boolean b = true;
        long l = 100500;
        byte by = 2;
        short s = 1;
        char c = 'k';
        LOG.debug("int: {}, float: {}, double: {}, boolean: {}, long: {}, byte: {}, short: {}, char: {}",
                i, f, d, b, l, by, s, c);

    }
}
