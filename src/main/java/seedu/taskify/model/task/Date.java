package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Task's date (and time) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format \"yyyy-mm-dd hh:mm\"";
    private static final String END_OF_DAY_TIME = "23:59";
    public final String value;
    private final LocalDateTime localDateTime;

    /**
     * Constructs a {@code Date}
     *
     * @param dateTimeString a valid string containing the date and time of the Task
     */
    public Date(String dateTimeString) {
        requireNonNull(dateTimeString);
        checkArgument(isValidDate(dateTimeString), MESSAGE_CONSTRAINTS);
        this.localDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.value = dateTimeString;
    }

    /**
     * Constructs a {@code Date} using a {@link LocalDateTime} instead
     */
    public Date(LocalDateTime localDateTime) {
        requireNonNull(localDateTime);
        checkArgument(isValidDate(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString()),
                MESSAGE_CONSTRAINTS);
        this.localDateTime = localDateTime;
        this.value = localDateTime.toString();
    }


    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test == null || !test.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("y-M-d H:m");
        df.setLenient(false);
        try {
            df.parse(test);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * Returns a Date object with today's date and time of 23:59.
     * @return Date object with today's date and time of 23:59.
     */
    public static Date endOfToday() {
        String todayDateString = LocalDate.now().toString();
        String todayDateTimeString = todayDateString + " " + END_OF_DAY_TIME;
        return new Date(todayDateTimeString);
    }

    /**
     * Checks if this Date is chronologically behind the {@code secondDate}
     * @param secondDate the Date being compared with
     * @return true if this Date is chronologically behind {@code secondDate}
     */
    public boolean isBefore(Date secondDate) {
        return localDateTime.isBefore(secondDate.getLocalDateTime());
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y-M-d H:m");
        return localDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return localDateTime.equals(date.localDateTime);
    }

    @Override
    public int hashCode() {
        return localDateTime.hashCode();
    }

    public int compareTo(Date date) {
        return this.localDateTime.compareTo(date.localDateTime);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
