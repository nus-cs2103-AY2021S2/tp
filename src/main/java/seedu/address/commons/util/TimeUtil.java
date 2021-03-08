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

    static {
        String[] patterns = new String[]{"HHmm"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        TIME_PARSER = builder.toFormatter();
    }

    public static LocalTime fromTimeInput(String string) throws ParseException {
        LocalTime time;

        try {
            time = TIME_PARSER.parse(string, LocalTime::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(String.format(MESSAGE_INVALID_TIME_FORMAT, "Only accepts HHmm, e.g. 1200"));
        }

        return time;
    }
}
