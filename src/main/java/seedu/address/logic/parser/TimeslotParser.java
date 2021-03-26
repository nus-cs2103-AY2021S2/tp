package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.appointment.exceptions.NegativeOrZeroDurationException;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Parses timeslots from user input.at higher level
 * FLow: UserInputParser ->TimeslotParser(arguemts) -> individual parsers like addappointmentprser
 *       ^any type of date -> converts to actual timeslot class -> pass thru like normal aguments as per prev
 *
 *       error -> correct format? if time not included or cannot read
 *       FOR PREFIX TIMESLOT_START
 *       ALTER EACH CASE and xxPARSER class - input only timeslot
 *       ALTER ENTIRE STATEMENT HERE- less work on prse commnd, introduce more bugs by replacing whole 'argument statement'
 *       "2021-01-01 00:00 "
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
     * Parses a {@code String dateTime} into a {@code LocalDateTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateTime} does not
     * conform to the expected date time format.
     */
    public static LocalDateTime parseDateTime(String userInput) throws ParseException {

        String formattedInput = userInput.toUpperCase().trim();
        String[] dateTimeInputArray = userInput.split(REMOVE_WHITESPACE_REGEX);
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

    public static StringBuilder parseFormat(String dateInput, String timeInput) {
        StringBuilder formatter = new StringBuilder("");

        if (dateInput.matches(TimeslotRegex.dateSlashShort)) {
            formatter.append("dd/mm/yy ");
        } else if (dateInput.matches(TimeslotRegex.dateSlashLong)) {
            formatter.append("dd/mm/yyyy ");
        } else if (dateInput.matches(TimeslotRegex.dateDashShort)) {
            formatter.append("dd-mm-yy ");
        } else {
            formatter.append("dd-mm-yyyy ");
        }
        return parseTimeFormat(timeInput, formatter);
    }

    public static StringBuilder parseTimeFormat(String timeInput, StringBuilder formatter) {
        if (timeInput.contains("AM") || timeInput.contains("PM")) {
            formatter.append("hh:mm a");
        } else {
            formatter.append("HH:mm");
        }
        return formatter;
        //check if formatter returned is most updated
    }

    public static LocalDateTime parseNextDateTime(String userInput) throws ParseException, NullPointerException {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            String[] nextDateTimeInputArray = userInput.split(REMOVE_WHITESPACE_REGEX);
            String keyword = nextDateTimeInputArray[1];
            String timeInput = nextDateTimeInputArray[2];
            LocalDateTime parsedDate = null;

            if (Arrays.stream(Day.values()).anyMatch(e -> e.name().equals(keyword))) {
                parsedDate = currentDateTime.with(TemporalAdjusters.next(DayOfWeek.valueOf(keyword)));
            } else if (userInput.contains("MONTH")) {
                parsedDate = currentDateTime.plusMonths(1);
            } else {
                parsedDate = currentDateTime.plusYears(1);
            }

            if (!timeInput.isEmpty()) {
                int[] hoursMinutesArray = parseTime(timeInput);
                parsedDate.withHour(hoursMinutesArray[0]);
                parsedDate.withMinute(hoursMinutesArray[1]);
            }
            return parsedDate;

        } catch (DateTimeParseException e) {
            throw new ParseException(MESSAGE_INVALID_NEXT_DATE_TIME_FORMAT);
        } catch (NullPointerException e) {
            throw new NullPointerException(MESSAGE_INVALID_TIME_FORMAT);
        }
    }

    public static int[] parseTime(String timeInput) throws NullPointerException {
        try {
            String[] hoursMinutesRawArray;
            timeInput = removeMeridian(timeInput);
            hoursMinutesRawArray = timeInput.split(":");
            int[] hoursMinutesIntegerArray = Arrays.stream(hoursMinutesRawArray).mapToInt(Integer::parseInt).toArray();

            if (timeInput.contains("PM")) {
                hoursMinutesIntegerArray[0] = (hoursMinutesIntegerArray[0]) + 12;
            }

            return hoursMinutesIntegerArray;

        } catch (NullPointerException e) {
            throw new NullPointerException(MESSAGE_INVALID_TIME_FORMAT);
        }
    }

    public static String removeMeridian(String timeInput) {
        String timeSubString = null;
        if ((timeInput != null) && (timeInput.length() > 0)) {
            timeSubString = timeInput.substring(timeInput.length() - 3);
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

