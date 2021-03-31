package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import seedu.address.logic.parser.exceptions.ParseException;

public class TimeUtil {

    private static final DateTimeFormatter TIME_PARSER;

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a");

    private static final String[] patterns;
    private static final String[] examples;

    static {
        patterns = new String[]{"HHmm"};
        examples = new String[]{"1200"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        TIME_PARSER = builder.toFormatter();
    }

    public static final String MESSAGE_CONSTRAINT = MESSAGE_INVALID_TIME_FORMAT + "Format given should be one of "
            + String.join(", ", patterns) + "\n"
            + "Some examples are " + String.join(", ", examples);

    /**
     * Takes a string and parses it into a LocalTime
     *
     * @param string the string to convert
     * @return a LocalTime that was created from the string
     */
    public static LocalTime fromTimeInput(String string) throws ParseException {
        LocalTime time;

        try {
            time = TIME_PARSER.parse(string, LocalTime::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(MESSAGE_CONSTRAINT);
        }

        return time;
    }

    public static String toUi(LocalTime localTime) {
        return DEFAULT_FORMATTER.format(localTime);
    }
}
