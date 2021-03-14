package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.commons.exceptions.DateConversionException;

/**
 * A class for encoding and decoding of Dates.
 */
public class DateUtil {

    /**
     * Encodes a date passed as a String into a LocalDate.
     * @param date Date in the yyyy-mm-dd format.
     * @return A LocalDate object.
     * @throws DateConversionException Occurs when a date had been passed in with the wrong format.
     */
    public static LocalDate encodeDate(String date) throws DateConversionException {
        requireNonNull(date);
        try {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            throw new DateConversionException("An error occurred while encoding date,"
                    + " use the dd-MM-yyyy format.");
        }
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the dd MMM yyyy format.
     * @param date A LocalDate object.
     * @return A date String in the dd MMM yyyy format.
     */
    public static String decodeDate(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Decodes a date passed as a LocalDate into a String in the yyyy-MM-dd format.
     * @param date A LocalDate object.
     * @return A date String in the yyyy-MM-dd format.
     */
    public static String decodeDateForStorage(LocalDate date) {
        requireNonNull(date);
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
