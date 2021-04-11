package seedu.module.model.task;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_TIME;
import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a Task's deadline in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class Time implements Comparable<Time> {

    public static final String MESSAGE_CONSTRAINTS = "Please enter a valid date or time!\n"
            + "Time should be formatted as yyyy-MM-dd or yyyy-MM-dd HH:mm";
    private static final String yearMonthDayString = "yyyy-MM-dd";
    private static final String timeString = "HH:mm";

    public static final DateTimeFormatter DATE_TIME_FORMATTER_WITH_TIME = DateTimeFormatter.ofPattern(
            yearMonthDayString + " " + timeString);

    public final String value;
    public final LocalDate date;
    public final LocalDateTime time;

    /**
     * Constructs a {@code Time}.
     *
     * @param timeString A valid timeString. Can either be in yyyy-MM-dd or yyyy-MM-dd HH:mm format
     */
    public Time(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);

        String[] timeStringArray = timeString.split(" ");
        String dateString = timeStringArray[0];

        date = parseDate(dateString, ISO_LOCAL_DATE);
        value = timeString;

        // only yyyy-MM-dd fields present
        if (!hasHoursMinutes(timeString)) {
            time = date.atTime(0, 0);
        } else {
            String hourMinutesString = timeStringArray[1];
            LocalTime hourMinutesField = parseHoursMinutes(hourMinutesString, ISO_LOCAL_TIME);
            time = date.atTime(hourMinutesField);
        }
    }

    /**
     * Method to make new Deadline using LocalDateTime object instead
     *
     * @param time a valid LocalDateTime that is used to construct {@code Time}.
     */
    public static Time makeTimeObject(LocalDateTime time) {
        requireNonNull(time);
        String deadlineString = time.format(DATE_TIME_FORMATTER_WITH_TIME);
        return new Time(deadlineString);
    }

    /**
     * Creates a new, correctly formatted time String
     * @param oldTime Time object to be incremented.
     * @param increment number of days to be added.
     * @return String representing the value of incremented time.
     */
    public static String makeNextTimeString(Time oldTime, long increment) {
        LocalDate newDate = oldTime.getDate().plusDays(increment);

        String dateString = newDate.format(ISO_LOCAL_DATE);

        if (hasHoursMinutes(oldTime.value)) {
            LocalTime hourMinuteField = oldTime.getTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES);
            String hourMinuteFieldString = hourMinuteField.toString();
            return dateString + " " + hourMinuteFieldString;
        } else {
            return dateString;
        }
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    /**
     * Checks for {@code timeString} containing HH:mm field
     * @param timeString String to be checked.
     * @return true if HH:mm substring present. False otherwise.
     */
    public static boolean hasHoursMinutes(String timeString) {
        assert timeString != null;
        String[] timeStringArray = timeString.split(" ");

        return timeStringArray.length > 1;
    }


    /**
     * Returns true if a given string is a valid time.
     * A valid time can either be in yyyy-MM-dd or yyyy-MM-dd HH:mm format.
     */
    public static boolean isValidTime(String test) {
        requireNonNull(test);
        if (!hasHoursMinutes(test)) {
            return isValidDate(test);
        } else {
            String[] testStringArr = test.split(" ");
            String yearMonthDaySubstring = testStringArr[0];
            String hourMinutesSubstring = testStringArr[1];

            return isValidDate(yearMonthDaySubstring) && isValidHoursMinutes(hourMinutesSubstring);
        }
    }

    private static LocalDate parseDate(String dateString, DateTimeFormatter formatter) throws DateTimeParseException {
        return LocalDate.parse(dateString, formatter);
    }

    private static boolean isValidDate(String dateString) {
        try {
            parseDate(dateString, ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            return false;
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    private static LocalTime parseHoursMinutes(String hoursMinutesString, DateTimeFormatter formatter)
            throws DateTimeParseException {

        return LocalTime.parse(hoursMinutesString, formatter);
    }

    private static boolean isValidHoursMinutes(String hoursMinutesString) {
        try {
            parseHoursMinutes(hoursMinutesString, DateTimeFormatter.ofPattern(timeString));
        } catch (DateTimeParseException e) {
            return false;
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Time // instanceof handles nulls
                && value.equals(((Time) other).value)); // state check
    }

    @Override
    public int compareTo(Time other) {
        return time.compareTo(other.getTime());
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
