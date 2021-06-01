package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_PARSER_TIME_CONSTRAINTS;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import seedu.address.commons.exceptions.TimeConversionException;

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
     *
     * @param time Time in the HHmm or HH:mm format.
     * @return A LocalTime object.
     * @throws TimeConversionException Occurs when a date had been passed in with the wrong format.
     */
    public static LocalTime encodeTime(String time) throws TimeConversionException {
        requireNonNull(time);
        try {
            return LocalTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new TimeConversionException(MESSAGE_PARSER_TIME_CONSTRAINTS);
        }
    }

    /**
     * Decodes a time passed as a LocalTime into a String in the HHmm format.
     *
     * @param time A LocalTime object.
     * @return A time String in the HHmm format.
     */
    public static String decodeTime(LocalTime time) {
        requireNonNull(time);
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

}
