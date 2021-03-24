package seedu.address.logic.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.logic.parser.exceptions.ParseException;


/**
 * A utility class for parsing/formatting LocalDateTimes into ISO format.
 */
public class DateTimeUtil {
    public static final String MESSAGE_INVALID_DATE_FORMAT =
            "The date should be of the following format : YYYY-MM-DD HH:MM";

    public static final DateTimeFormatter ISO_DATE_FORMATTER_NO_SECONDS =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static final DateTimeFormatter PRETTY_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd MMM yyyy, eeee , hh:mm a");


    /**
     * Formats a LocalDateTime object into the appropriate ISO string, with the seconds
     * stripped.
     * @param dateTime the dateTime object to be formatted.
     * @return the formatted string
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(ISO_DATE_FORMATTER_NO_SECONDS);
    }

    /**
     * Parses a string into LocalDateTime.
     * @param dateTime the string must be in the format yyyy-MM-dd HH:mm
     * @return the LocalDateTime.
     * @throws ParseException if the dateTime string is of incorrect format
     */

    public static LocalDateTime parseDateTime(String dateTime) throws ParseException {
        try {
            return LocalDateTime.parse(dateTime, ISO_DATE_FORMATTER_NO_SECONDS);
        } catch (Exception e) {
            throw new ParseException(MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    /**
     * Formats a LocalDateTime object into a pretty print format.
     * @param dateTime the dateTime object to be formatted.
     * @return the formatted string
     */
    public static String prettyPrintFormatDateTime(LocalDateTime dateTime) {
        return dateTime.format(PRETTY_DATE_FORMATTER);
    }


}
