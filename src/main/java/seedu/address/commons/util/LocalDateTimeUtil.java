package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class LocalDateTimeUtil {
    public static final String DATE_TIME_CONSTRAINTS =
            "DateTime should be a valid date and time in the format of dd/MM/yyyy HH:mm. e.g. 29/11/2020 23:59";

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm")
            .withResolverStyle(ResolverStyle.SMART);

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy")
            .withResolverStyle(ResolverStyle.SMART);

    /**
     * Returns true if a given string is a valid dateTime number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDateTime(String test) {
        requireNonNull(test);
        try {
            LocalDateTime.parse(test, DATETIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if a given string is a valid date number.
     *
     * @param test the string value to be put to test.
     * @return true if the test string is valid and false otherwise
     */
    public static boolean isValidDate(String test) {
        requireNonNull(test);
        try {
            LocalDate.parse(test, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }


}
