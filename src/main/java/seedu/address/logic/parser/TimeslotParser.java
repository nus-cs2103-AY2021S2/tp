package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

/**
 * Contains Utility methods used for parsing dates, time, and timeslots from raw user inputs
 */
public class TimeslotParser {
    public static final String MESSAGE_INVALID_DATE_TIME_FORMAT = "Input format for date time parameters "
            + "must be: YYYY-MM-DD HH:mm";
    public static final String MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT = "Input format for next date time parameters ";
    public static final String MESSAGE_INVALID_TIME_FORMAT = "Input format for time parameters must be: ";
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
        String[] dateTimeInputArray = formattedInput.split(REMOVE_WHITESPACE_REGEX);
        if (formattedInput.contains("NEXT")) {
            return parseNextDateTime(formattedInput);
        } else {
            try {
                String dateInput = dateTimeInputArray[0];
                String timeInput = dateTimeInputArray[1];

                StringBuilder parsedFormat = parseFormat(dateInput, timeInput);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(String.valueOf(parsedFormat));
                return LocalDateTime.parse(formattedInput, formatter);
            } catch (DateTimeParseException e) {
                throw new ParseException(MESSAGE_INVALID_DATE_TIME_FORMAT);
            }
        }
    }

    /**
     * Parses a {@code String dateInput, String timeInput} into their appropriate LocalDateTime
     * format in {@code StringBuilder}. For Absolute DateTime cases only.
     */
    public static StringBuilder parseFormat(String dateInput, String timeInput) {
        StringBuilder formatter = new StringBuilder("");

        if (dateInput.matches(TimeslotRegex.DATE_SLASH_SHORT)) {
            formatter.append("dd/MM/yy ");
        } else if (dateInput.matches(TimeslotRegex.DATE_SLASH_LONG)) {
            formatter.append("dd/MM/yyyy ");
        } else if (dateInput.matches(TimeslotRegex.DATE_DASH_SHORT)) {
            formatter.append("dd-MM-yy ");
        } else {
            formatter.append("dd-MM-yyyy ");
        }
        return parseTimeFormat(timeInput, formatter);
    }

    /**
     * Parses a {@code String timeInput, StringBuilder} into a full {@code StringBuilder} format for
     * LocalDateTime. 24-Hour Clock and Meridian time Inputs are both accepted.
     */
    public static StringBuilder parseTimeFormat(String timeInput, StringBuilder formatter) {
        if (timeInput.contains("AM") || timeInput.contains("PM")) {
            formatter.append("hh:mma");
        } else {
            formatter.append("HH:mm");
        }
        return formatter;
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
    public static LocalDateTime parseNextDateTime(String userInput) throws ParseException, NullPointerException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime currentDateTime = LocalDateTime.now().withSecond(0).withNano(0);
            String[] nextDateTimeInputArray = userInput.split(REMOVE_WHITESPACE_REGEX);
            String keyword = nextDateTimeInputArray[1];
            LocalDateTime parsedDate = null;

            if (Arrays.stream(Day.values()).anyMatch(e -> e.name().equals(keyword))) {
                parsedDate = currentDateTime.with(TemporalAdjusters.next(DayOfWeek.valueOf(keyword)));
            } else if (keyword.contains("MONTH")) {
                parsedDate = currentDateTime.plusMonths(1);
            } else {
                parsedDate = currentDateTime.plusYears(1);
            }

            if (nextDateTimeInputArray.length > 2) {
                String timeInput = nextDateTimeInputArray[2];
                int[] hoursMinutesArray = parseTime(timeInput);
                parsedDate = parsedDate.withHour(hoursMinutesArray[0]);
                parsedDate = parsedDate.withMinute(hoursMinutesArray[1]);
                parsedDate = parsedDate.withSecond(0).withNano(0);
            }

            return parsedDate;

        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT);
        } catch (NullPointerException e) {
            throw new NullPointerException(MESSAGE_INVALID_TIME_FORMAT);
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
    public static int[] parseTime(String timeInput) throws NullPointerException {
        try {
            String revisedTimeInput = removeMeridian(timeInput);
            String[] hoursMinutesRawArray = revisedTimeInput.split(":");
            int[] hoursMinutesIntegerArray = Arrays.stream(hoursMinutesRawArray).mapToInt(Integer::parseInt).toArray();
            if (timeInput.contains("PM")) {
                hoursMinutesIntegerArray[0] = (hoursMinutesIntegerArray[0]) + 12;
            }

            if (timeInput.contains("AM") && hoursMinutesIntegerArray[0] == 12) {
                hoursMinutesIntegerArray[0] = 0;
            }

            return hoursMinutesIntegerArray;

        } catch (NullPointerException e) {
            throw new NullPointerException(MESSAGE_INVALID_TIME_FORMAT);
        }
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

enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
