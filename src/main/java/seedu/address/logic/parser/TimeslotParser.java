package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESLOT_START;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

/**
 * Contains Utility methods used for parsing dates, time, and timeslots from raw user inputs
 */
public class TimeslotParser {
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Input format for date time parameters "
            + "must be any of:\n"
            + "==== Date ====\n"
            + "> DD-MM-YYYY\n"
            + "> DD-MM-YY\n"
            + "> DD/MM/YYYY\n"
            + "> DD/MM/YY\n"
            + "> next DAY\n"
            + "> next month\n"
            + "> next year\n"
            + "==== Time ====\n"
            + "> hh:mm (In 24-Hour format)\n"
            + "> hh:mmam/pm\n"
            + "Example:\n" + PREFIX_TIMESLOT_START
            + "12/12/21 01:15pm or 12-12-21 13:15";
    public static final String MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT = "Input format for next date time parameters "
            + "must be:\n"
            + "next DAY TIME\n"
            + "Example:\n" + PREFIX_TIMESLOT_START
            + "next Wednesday 12:12pm";
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Input format for time parameters must be: "
            + "hh:mm (In 24-Hour format) or hh:mmam/pm\n"
            + "Example:\n"
            + "15:12 or 03:12pm";
    public static final String MESSAGE_INVALID_PAST_DATE_TIME_FORMAT = "Invalid date and time!\n"
            + "This timeslot has already occurred in the past."
            + "Please input Date and Times that have not yet occurred as of now.";
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
     * date formats or the next relevant dateTime.
     *
     * @throws ParseException if the given {@code dateTime} does not
     * conform to the expected date time format.
     */
    public static LocalDateTime parseDateTime(String userInput) throws ParseException {
        String formattedInput = userInput.toUpperCase().trim();
        boolean isOldDateTime = false;

        if (formattedInput.contains("NEXT")) {
            return parseNextDateTime(formattedInput);
        } else {
            for (DateTimeFormat dateTimeFormat : DateTimeFormat.values()) {
                try {
                    LocalDateTime timeslotInput = LocalDateTime.parse(formattedInput,
                            dateTimeFormat.getDateTimeFormatter());
                    if (timeslotInput.compareTo(LocalDateTime.now()) < 0) {
                        isOldDateTime = true;
                        break;
                    }
                    return timeslotInput;
                } catch (DateTimeParseException e) {
                    continue;
                }
            }
            String messageUsage = isOldDateTime ? MESSAGE_INVALID_PAST_DATE_TIME_FORMAT
                    : MESSAGE_INVALID_DATE_TIME_FORMAT;
            throw new ParseException(messageUsage);
        }
    }

    /**
     * Parses a {@code String userInput} into a {@code LocalDateTime}.
     * Parses user input containing the next day, month or year. Time can remain the same or
     * revised into a new one as per user input.
     *
     * @throws ParseException if the given {@code dateTime} does not
     * conform to the expected date time format.
     * @throws NullPointerException if the {@code timeInput} does not
     * conform to the expected time format.
     */
    public static LocalDateTime parseNextDateTime(String userInput) throws ParseException {
        boolean isInvalidTime = false;
        final Pattern nextDateTimeFormat = Pattern.compile("(?<nextKeyword>\\w+)\\s(?<nextTimePeriod>\\w+)"
                        + "\\s(?<timeInput>.*)", Pattern.CASE_INSENSITIVE);
        final Matcher nextDateTimeMatcher = nextDateTimeFormat.matcher(userInput);

        try {
            LocalDateTime currentDateTime = LocalDateTime.now().withSecond(0).withNano(0);
            LocalDateTime parsedDate = null;

            if (nextDateTimeMatcher.matches()) {
                final String nextTimePeriod = nextDateTimeMatcher.group("nextTimePeriod");
                final String timeInput = nextDateTimeMatcher.group("timeInput").trim();

                if (Arrays.stream(Day.values()).anyMatch(e -> e.name().equals(nextTimePeriod))) {
                    parsedDate = currentDateTime.with(TemporalAdjusters.next(DayOfWeek.valueOf(nextTimePeriod)));
                } else if (nextTimePeriod.contains("MONTH")) {
                    parsedDate = currentDateTime.plusMonths(1);
                } else if (nextTimePeriod.contains("YEAR")) {
                    parsedDate = currentDateTime.plusYears(1);
                } else {
                    isInvalidTime = true;
                }

                int[] hoursMinutesArray = parseTime(timeInput);
                parsedDate = parsedDate.withHour(hoursMinutesArray[0]);
                parsedDate = parsedDate.withMinute(hoursMinutesArray[1]);
                parsedDate = parsedDate.withSecond(0).withNano(0);

            } else {
                throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
            }

            return parsedDate;

        } catch (DateTimeParseException e) {
            String messageUsage = isInvalidTime ? MESSAGE_INVALID_TIME_FORMAT
                    : MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT;
            throw new ParseException(messageUsage);
        }
    }

    /**
     * Parses a {@code String timeInput} into a {@code LocalDateTime}.
     * Adjusts the time in current LocalDateTime based on user time input.
     * Accommodates both 24-clock or Meridian time format.
     *
     * @throws NullPointerException if the {@code timeInput} does not
     * conform to the expected time format.
     */
    public static int[] parseTime(String timeInput) {

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
     *
     * @throws NullPointerException if the {@code timeInput} does not
     * conform to the expected time format.
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
    YYYY_DASH_MM_DASH_DD_HR("yyyy-MM-dd HH:mm");

    private String dateTimePattern;

    private DateTimeFormat(String dateTimePattern) {
        this.dateTimePattern = dateTimePattern;
    }

    public DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public String getDateTimePattern() {
        return dateTimePattern;
    }
}
