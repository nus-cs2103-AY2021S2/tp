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

    public static final DateTimeFormatter DATE_INPUT_FORMATTER; // used to create user inputs for testing

    private static final DateTimeFormatter DATE_PARSER;

    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static final DateTimeFormatter ERROR_MESSAGE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static final DateTimeFormatter NO_YEAR_FORMATTER = DateTimeFormatter.ofPattern("dd MMM");

    private static final String[] patterns;
    private static final String[] toShowPatterns; // patterns to present to users in help text
    private static final String[] toShowExamples;

    static {
        patterns = new String[]{"d-M-yyyy", "d-MM-yyyy", "dd-M-yyyy", "dd-MM-yyyy"};
        toShowPatterns = new String[]{"dd-MM-yyyy"};
        toShowExamples = new String[]{"12-12-2020"};

        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

        Arrays.stream(patterns).map(DateTimeFormatter::ofPattern).forEach(builder::appendOptional);
        DATE_PARSER = builder.toFormatter();

        DATE_INPUT_FORMATTER = DateTimeFormatter.ofPattern(toShowPatterns[0]);
    }

    public static final String MESSAGE_CONSTRAINT = MESSAGE_INVALID_DATE_FORMAT
            + "Format given should be one of " + String.join(", ", toShowPatterns) + "."
            + " Some examples are " + String.join(", ", toShowExamples) + ".";

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

    public static boolean isToday(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }

    public static boolean afterToday(LocalDate date) {
        return date.isAfter(LocalDate.now());
    }

    public static String toUi(LocalDate localDate) {
        return DEFAULT_FORMATTER.format(localDate);
    }

    public static String toUiNoYear(LocalDate localDate) {
        return NO_YEAR_FORMATTER.format(localDate);
    }

    public static String toErrorMessage(LocalDate localDate) {
        return ERROR_MESSAGE_FORMATTER.format(localDate);
    }

    public static String toString(LocalDate localDate, DateTimeFormatter dateFormatter) {
        return dateFormatter.format(localDate);
    }
}
