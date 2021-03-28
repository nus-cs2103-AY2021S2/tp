package seedu.address.commons.util;

import seedu.address.commons.exceptions.DateConversionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_DATE_CONSTRAINTS;

/**
 * A class for encoding and decoding of Time.
 */
public class TimeUtil {

    private static DateTimeFormatter timeFormat1 = DateTimeFormatter.ofPattern("HHmm");
    private static DateTimeFormatter timeFormat2 = DateTimeFormatter.ofPattern("HH:mm");

    private static DateTimeFormatter formatter =
            new DateTimeFormatterBuilder()
                    .appendOptional(timeFormat1)
                    .appendOptional(timeFormat2)
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Encodes a time passed as a String into a LocalTime.
     * @param time Time in the HH:mm format.
     * @return A LocalTime object.
     * @throws DateConversionException Occurs when a date had been passed in with the wrong format.
     */
    public static LocalTime encodeDate(String time) throws DateConversionException {
        requireNonNull(date);
        try {
            return checkDateIsNotNegative(LocalDate.parse(date, formatter));
        } catch (DateTimeParseException e) {
            throw new DateConversionException(MESSAGE_PARSER_DATE_CONSTRAINTS);
        }
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the dd MMM uuuu format.
     * @param date A LocalDate object.
     * @return A date String in the dd MMM uuuu format.
     */
    public static String decodeDate(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the EEEE, dd MMM uuuu format.
     * @param date A LocalDate object.
     * @return A date String in the EEEE, dd MMM uuuu format.
     */
    public static String decodeDateWithDay(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMM uuuu"));
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the uuuu-MM-dd format.
     * @param date A LocalDate object.
     * @return A date String in the uuuu-MM-dd format.
     */
    public static String decodeDateForStorage(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-uuuu"));
    }

    /**
     * Checks if year is negative.
     *
     * @param date {@code LocalDate} to check.
     * @return {@code date} if year is non-negative.
     * @throws DateConversionException if {@code date} is negative.
     */
    private static LocalDate checkDateIsNotNegative(LocalDate date) throws DateConversionException {
        if (date.isBefore(LocalDate.ofYearDay(0, 1))) {
            throw new DateConversionException("Year should be a non-negative 4 digit number.");
        }

        return date;
    }
}
