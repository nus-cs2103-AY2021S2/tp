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

    private static final String[] patterns;
    private static final String[] examples;

    static {
        patterns = new String[]{"dd-MM-yyyy"};
        examples = new String[]{"12-12-2020"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        DATE_PARSER = builder.toFormatter();
    }

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
            String errorMsg = "Format given should be one of " + String.join(", ", patterns) + "\n"
                    + "Some examples are " + String.join(", ", examples);

            throw new ParseException(String.format(MESSAGE_INVALID_DATE_FORMAT, errorMsg));
        }

        return date;
    }

    public static String toUi(LocalDate localDate) {
        return DEFAULT_FORMATTER.format(localDate);
    }
}
