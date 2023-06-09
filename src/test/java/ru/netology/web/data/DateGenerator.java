package ru.netology.web.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateGenerator {

    public static String generateDate(long addDays, String pattern) {

        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));

    }
}
