package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

/**
 * Contains Utility methods used for parsing dates, time, and timeslots from raw user inputs
 */
public class TimeslotParser {
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Invalid Date Time Format!\n"
            + "Input format for date time parameters must be any of:\n"
            + "at/[DATE] [TIME]\n"
            + "==== Date ====\n"
            + "> DD-MM-YYYY\n"
            + "> DD-MM-YY\n"
            + "> DD/MM/YYYY\n"
            + "> DD/MM/YY\n"
            + "> YYYY-MM-DD\n"
            + "> YY-MM-DD\n"
            + "> next DAY\n"
            + "> next month\n"
            + "> next year\n"
            + "==== Time ====\n"
            + "> hh:mm (In 24-Hour format)\n"
            + "> hh:mmam/pm\n"
            + "Example:\n" + PREFIX_TIMESLOT_START
            + "12/12/21 01:15pm or 12-12-21 13:15";
    public static final String MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT = "Invalid Date Time Format with next keyword!\n"
            + "Abbreviations for Days are not allowed.\n"
            + "Input format for next date time parameters must be:\n"
            + "at/next [DAY] [TIME] or next [DAY]\n"
            + "Example:\n" + PREFIX_TIMESLOT_START
            + "next Wednesday 12:12pm or next Wednesday";
    public static final String MESSAGE_INVALID_DURATION_FORMAT = "Input format for duration must be: "
            + "[%d UNIT...] where units are { H:hours, M:minutes }\n"
            + "Examples of duration inputs:\n"
            + "2H 30M - returns a duration of 2 hours, 30 minutes and 30 seconds\n"
            + "150M   - returns a duration of 150 minutes";
    private static final String PREFIX_DURATION_PARSE_SEQUENCE = "PT";
    private static final String REMOVE_WHITESPACE_REGEX = "\\s+";

    /**
     * Parses a {@code String userInput} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed. Raw User Input set to all Uppercase.
     * Multiple user Input formats are accepted and parsed accordingly, mainly in absolute
     * date formats or the next relevant LocalDateTime.
     *
     * @throws ParseException for the following scenarios:
     * - if the given {@code LocalDateTime} does not conform to the expected date time format.
     */
    public static LocalDateTime parseDateTime(String userInput) throws ParseException {
        String formattedInput = userInput.toUpperCase().trim();

        if (formattedInput.contains("NEXT")) {
            return parseNextDateTime(formattedInput);
        } else {
            for (DateTimeFormat dateTimeFormat : DateTimeFormat.values()) {
                try {
                    LocalDateTime timeslotInput = LocalDateTime.parse(formattedInput,
                            dateTimeFormat.getDateTimeFormatter());
                    return timeslotInput;
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
            throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
    }

    /**
     * Parses a {@code String userInput} into a {@code LocalDateTime}.
     * Parses user input containing the next day, month or year. Time can remain the same or
     * revised into a new one as per user input.
     *
     * @throws ParseException if the given {@code LocalDateTime} does not
     * conform to the expected date time format.
     */
    public static LocalDateTime parseNextDateTime(String userInput) throws ParseException {
        final Pattern nextDateTimeFormat = Pattern.compile("(?<nextKeyword>\\w+)\\s(?<nextDatePeriod>\\w+)"
                        + "\\s(?<timeInput>.*)", Pattern.CASE_INSENSITIVE);
        final Matcher nextDateTimeMatcher = nextDateTimeFormat.matcher(userInput);

        //for inputs with no user-specified time
        final Pattern nextDateFormat = Pattern.compile("(?<nextKeyword>\\w+)\\s(?<nextDatePeriod>\\w+)",
                Pattern.CASE_INSENSITIVE);
        final Matcher nextDateMatcher = nextDateFormat.matcher(userInput);

        try {
            LocalDateTime parsedDate = null;

            if (nextDateTimeMatcher.matches()) {
                final String nextDatePeriod = nextDateTimeMatcher.group("nextDatePeriod");
                final String timeInput = nextDateTimeMatcher.group("timeInput").trim();

                parsedDate = parseNextDate(nextDatePeriod);

                int[] hoursMinutesArray = parseNextTime(timeInput);
                parsedDate = parsedDate.withHour(hoursMinutesArray[0]);
                parsedDate = parsedDate.withMinute(hoursMinutesArray[1]);

            } else if (nextDateMatcher.matches()) {
                final String nextDatePeriod = nextDateMatcher.group("nextDatePeriod");
                parsedDate = parseNextDate(nextDatePeriod);

            } else {
                throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
            }

            return parsedDate;

        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT);
        }
    }

    /**
     * Parses a {@code String nextDatePeriod} into a {@code LocalDateTime}.
     * Adjusts the date in current LocalDateTime based on user next date input.
     * Flips {@code isInvalidTime} if
     */
    public static LocalDateTime parseNextDate(String nextDatePeriod) throws ParseException {
        LocalDateTime currentDateTime = LocalDateTime.now().withSecond(0).withNano(0);
        LocalDateTime parsedDate = null;

        if (Arrays.stream(Day.values()).anyMatch(e -> e.name().equals(nextDatePeriod))) {
            parsedDate = currentDateTime.with(TemporalAdjusters.next(DayOfWeek.valueOf(nextDatePeriod)));
        }

        if (nextDatePeriod.contains("MONTH")) {
            parsedDate = currentDateTime.plusMonths(1);
        }
        if (nextDatePeriod.contains("YEAR")) {
            parsedDate = currentDateTime.plusYears(1);
        }

        if (parsedDate == null) {
            throw new ParseException(MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT);
        } else {
            return parsedDate;
        }
    }

    /**
     * Parses a {@code String timeInput} into a {@code LocalDateTime}.
     * Adjusts the time in current LocalDateTime based on user time input.
     * Accommodates both 24-clock or Meridian time format.
     */
    public static int[] parseNextTime(String timeInput) {

        String revisedTimeInput = (timeInput.contains("PM") || timeInput.contains("AM"))
                ? removeMeridian(timeInput)
                : timeInput;

        String[] hoursMinutesRawArray = revisedTimeInput.split(":");
        int[] hoursMinutesIntegerArray = Arrays.stream(hoursMinutesRawArray).mapToInt(Integer::parseInt).toArray();

        if (timeInput.contains("PM")) {
            hoursMinutesIntegerArray[0] = (hoursMinutesIntegerArray[0]) + 12;
        }

        if (timeInput.contains("AM") && hoursMinutesIntegerArray[0] == 12) {
            hoursMinutesIntegerArray[0] = 0;
        }

        return hoursMinutesIntegerArray;
    }

    /**
     * Removes Meridian format from a {@code String timeInput} to {@code String}.
     * Gets rid of Meridian Format of "AM" and "PM" in raw user time input.
     */
    public static String removeMeridian(String timeInput) {
        String timeSubString = null;
        if ((timeInput != null) && (timeInput.length() > 0)) {
            timeSubString = timeInput.substring(0, timeInput.length() - 2);
        }
        return timeSubString;
    }

    /**
     * Parses a {@code String duration} into a {@code Duration}.
     * All whitespaces will be removed.
     *
     * @throws ParseException if the given {@code duration} does not
     * conform to the expected duration format.
     */
    public static Duration parseDuration(String duration) throws ParseException {
        requireNonNull(duration);
        try {
            return Duration.parse(PREFIX_DURATION_PARSE_SEQUENCE + duration.replaceAll(
                    REMOVE_WHITESPACE_REGEX, ""));
        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_DURATION_FORMAT);
        }
    }

    /**
     * Parses a {@code String userInput} into a {@code Date}.
     *
     * @throws ParseException if the given {@code Date} does not conform to the expected date time format.
     */
    public static Date parseStandardDate(String userInput) throws ParseException {
        for (StandardDateFormat standardDateFormat : StandardDateFormat.values()) {
            try {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(standardDateFormat.getDatePattern());
                simpleDateFormat.setLenient(false);
                return simpleDateFormat.parse(userInput);
            } catch (java.text.ParseException e) {
                continue;
            }
        }
        throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
    }

    /**
     * Parses a {@code String userInput} in 12-Hour clock format into a {@code String} in 24-Hour clock format.
     *
     * @throws ParseException if the given {@code String} does not conform to the expected time format.
     */
    public static String parseStandardTime(String userInput) throws ParseException {
        try {
            SimpleDateFormat meridianFormat = new SimpleDateFormat("hh:mma");
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm");
            return hourFormat.format(meridianFormat.parse(userInput));

        } catch (java.text.ParseException e) {
            throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
        }
    }

    /**
     * Parses a {@code String start} and {@code String end} into a {@code Timeslot}.
     *
     * @throws ParseException for the following scenarios:
     * - the given {@code start} or {@code end} does not conform to the expected date
     * time format
     * - the {@code LocalDateTime} represented by {@code end} is not strictly after
     * {@code start}
     */
    public static Timeslot parseTimeslotByEnd(String start, String end) throws ParseException {
        try {
            return new Timeslot(parseDateTime(start), parseDateTime(end));
        } catch (NegativeOrZeroDurationException e) {
            throw new ParseException(Timeslot.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String start} and {@code String duration} into a {@code Timeslot}.
     *
     * @throws ParseException for the following scenarios:
     * - the given {@code start} does not conform to the expected date time format
     * - the given {@code duration} does not conform to the expected duration format
     * - the given {@code Duration} represented by {@code duration} is negative.
     */
    public static Timeslot parseTimeslotByDuration(String start, String duration) throws ParseException {
        try {
            return new Timeslot(parseDateTime(start), parseDuration(duration));
        } catch (NegativeOrZeroDurationException e) {
            throw new ParseException(Timeslot.MESSAGE_CONSTRAINTS);
        }
    }
}

/**
 * This documents the Days of the Week recognised by the parser for "next" commands
 */
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

/**
 * This documents the Days of the Week recognised by the parser for "next" commands.
 * Includes formats for absolute datess and time in 24-Hour Clock and Meridian Clock.
 */
enum DateTimeFormat {
    DD_SLASH_MM_SLASH_YY_HR("dd/MM/yy HH:mm"),
    DD_SLASH_MM_SLASH_YY_MERIDIAN("dd/MM/yy hh:mma"),
    DD_SLASH_MM_SLASH_YYYY_HR("dd/MM/yyyy HH:mm"),
    DD_SLASH_MM_SLASH_YYYY_MERIDIAN("dd/MM/yyyy hh:mma"),
    DD_DASH_MM_DASH_YY_HR("dd-MM-yy HH:mm"),
    DD_DASH_MM_DASH_YY_MERIDIAN("dd-MM-yy hh:mma"),
    DD_DASH_MM_DASH_YYYY_HR("dd-MM-yyyy HH:mm"),
    DD_DASH_MM_DASH_YYYY_MERIDIAN("dd-MM-yyyy hh:mma"),
    YYYY_DASH_MM_DASH_DD_HR("yyyy-MM-dd HH:mm"),
    YYYY_DASH_MM_DASH_DD_MERIDIAN("yyyy-MM-dd hh:mma"),
    YY_DASH_MM_DASH_DD_HR("yy-MM-dd HH:mm"),
    YY_DASH_MM_DASH_DD_MERIDIAN("yy-MM-dd hh:mma");


    private String dateTimePattern;

    DateTimeFormat(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }
}

enum StandardDateFormat {
    DD_SLASH_MM_SLASH_YY("dd/MM/yy"),
    DD_SLASH_MM_SLASH_YYYY("dd/MM/yyyy"),
    DD_DASH_MM_DASH_YY("dd-MM-yy"),
    DD_DASH_MM_DASH_YYYY("dd-MM-yyyy"),
    YYYY_DASH_MM_DASH_DD("yyyy-MM-dd"),
    YY_DASH_MM_DASH_DD("yy-MM-dd");

    private String datePattern;

    StandardDateFormat(String datePattern) {
        this.datePattern = datePattern;
    }

    public String getDatePattern() {
        return datePattern;
    }
}
