package seedu.address.commons.util;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATE_FORMAT;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import seedu.address.logic.parser.exceptions.ParseException;

public class DateUtil {

    public static final LocalDate ZERO_DAY = LocalDate.ofInstant(Instant.ofEpochMilli(0), ZoneId.systemDefault());

    private static final DateTimeFormatter DATE_PARSER;

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    private static final DateTimeFormatter NO_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd MMM");

    private static final String[] patterns;
    private static final String[] examples;

    static {
        patterns = new String[]{"dd-MM-yyyy"};
        examples = new String[]{"12-12-2020"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        DATE_PARSER = builder.toFormatter();
    }

    public static final String MESSAGE_CONSTRAINT = MESSAGE_INVALID_DATE_FORMAT + "Format given should be one of "
        + String.join(", ", patterns) + "\n"
        + "Some examples are " + String.join(", ", examples);

    /**
     * Takes a string and parses it into a LocalDate
     *
     * @param string the string to convert
     * @return a LocalDate that was created from the string
     */
    public static LocalDate fromDateInput(String string) throws ParseException {
        LocalDate date;

        try {
            date = DATE_PARSER.parse(string, LocalDate::from);
        } catch (DateTimeParseException dte) {
            throw new ParseException(MESSAGE_CONSTRAINT);
        }

        return date;
    }

    public static String toString(LocalDate localDate, DateTimeFormatter dateFormatter) {
        return dateFormatter.format(localDate);
    }

    public static String toUi(LocalDate localDate) {
        return DEFAULT_FORMATTER.format(localDate);
    }

    public static String toUiNoYear(LocalDate localDate) {
        return NO_YEAR_FORMATTER.format(localDate);
    }
}
