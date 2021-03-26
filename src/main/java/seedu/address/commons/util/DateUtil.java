package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_DATE_CONSTRAINTS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.commons.exceptions.DateConversionException;

/**
 * A class for encoding and decoding of Dates.
 */
public class DateUtil {
    /*
        Inconsistency in naming of year: uuuu in formatters, yyyy in error messages.
        Java uses uuuu for years (yyyy is year-of-era).
        However, yyyy is the more commonly accepted symbol for year and will be more intuitive for users.
     */
    private static DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    private static DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("ddMMuuuu");
    private static DateTimeFormatter dateFormat3 = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    private static DateTimeFormatter dateformat4 = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    private static DateTimeFormatter formatter =
            new DateTimeFormatterBuilder()
                    .appendOptional(dateFormat1)
                    .appendOptional(dateFormat2)
                    .appendOptional(dateFormat3)
                    .appendOptional(dateformat4)
                    .toFormatter()
                    .withResolverStyle(ResolverStyle.STRICT);

    /**
     * Encodes a date passed as a String into a LocalDate.
     * @param date Date in the yyyy-mm-dd format.
     * @return A LocalDate object.
     * @throws DateConversionException Occurs when a date had been passed in with the wrong format.
     */
    public static LocalDate encodeDate(String date) throws DateConversionException {
        requireNonNull(date);
        try {
            return checkDateIsNotNegative(LocalDate.parse(date, formatter));
        } catch (DateTimeParseException e) {
            throw new DateConversionException(MESSAGE_PARSER_DATE_CONSTRAINTS);
        }
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the dd MMM yyyy format.
     * @param date A LocalDate object.
     * @return A date String in the dd MMM yyyy format.
     */
    public static String decodeDate(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the yyyy-MM-dd format.
     * @param date A LocalDate object.
     * @return A date String in the yyyy-MM-dd format.
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
