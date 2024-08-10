package com.pizza.awesomepizza.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class DateUtils {

    /**
     * Gets the start of the current day in UTC as a LocalDateTime.
     *
     * @return a LocalDateTime representing the start of the current day in UTC
     */
    public static LocalDateTime startOfDay() {
        return startOfDay(LocalDate.now(ZoneOffset.UTC));
    }

    /**
     * Converts a given LocalDate to the start of that day as a LocalDateTime.
     *
     * @param date the LocalDate to be converted
     * @return a LocalDateTime representing the start of the given date
     */
    public static LocalDateTime startOfDay(LocalDate date) {
        return date.atStartOfDay();
    }

}
