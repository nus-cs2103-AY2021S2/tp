package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import seedu.address.logic.parser.exceptions.ParseException;

public class DateUtil {

    private static final DateTimeFormatter DATE_PARSER;

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    static {
        String[] patterns = new String[]{"dd-MM-yyyy"};
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        DATE_PARSER = builder.toFormatter();
    }

    public static LocalDate fromDateInput(String string) throws ParseException {
        LocalDate date;

        try {
            date = DATE_PARSER.parse(string, LocalDate::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT,
                    "Only accept dd-MM-yyyy, e.g. 12-12-2020"));
        }

        return date;
    }

    public static String toUi(LocalDate localDate) {
        return DEFAULT_FORMATTER.format(localDate);
    }
}
