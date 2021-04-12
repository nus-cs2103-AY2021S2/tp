package seedu.taskify.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.taskify.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

/**
 * Represents a Task's date (and time) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String CORRECT_FORMAT_BUT_INVALID_DATE =
            "Maybe check if your specified date is an actual date and time?";
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format \"yyyy-mm-dd hh:mm\"";
    public static final String MESSAGE_CONSTRAINTS_WITHOUT_TIME = "For the view command, Date should "
            + "be of the format \"yyyy-mm-dd\"";
    public static final String NOT_LEAP_YEAR_ERROR = "Oops! The specified date is not a leap year!";
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
        checkArgument(isValidDate(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))),
                MESSAGE_CONSTRAINTS);
        this.localDateTime = localDateTime;
        this.value = localDateTime.toString();
    }


    /**
     * Returns if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (!Date.isCorrectInputFormat(test)) {
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
     * Returns if a given string is a valid date without time.
     */
    public static boolean isValidDateWithoutTime(String test) {
        if (!Date.isCorrectDateFormat(test)) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("y-M-d");
        df.setLenient(false);
        try {
            df.parse(test);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    /**
     * Returns if a given String is of correct format ("yyyy-MM-dd HH:mm").
     */
    public static boolean isCorrectInputFormat(String inputDate) {
        return inputDate != null && inputDate.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}");
    }

    /**
     * Returns if a given String is of the following format ("yyyy-MM-dd").
     */
    public static boolean isCorrectDateFormat(String inputDate) {
        return inputDate != null && inputDate.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    /**
     * Returns if a given String date is of 29th February but not on a leap year.
     */
    public static boolean isInvalidLeapYearDate(String inputDate) {
        if (Date.isCorrectInputFormat(inputDate)) {
            String[] splitArr = inputDate.split("-");
            int year = Integer.parseInt(splitArr[0]);
            String month = splitArr[1];
            String date = splitArr[2].split(" ")[0];
            if (!isLeapYear(year) && month.equals("02") && date.equals("29")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if a given String date is of 29th February but not on a leap year.
     */
    public static boolean isInvalidLeapYearDateWithoutTime(String inputDate) {
        if (Date.isCorrectDateFormat(inputDate)) {
            String[] splitArr = inputDate.split("-");
            int year = Integer.parseInt(splitArr[0]);
            String month = splitArr[1];
            String date = splitArr[2];
            if (!isLeapYear(year) && month.equals("02") && date.equals("29")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns if a given int is a leap year.
     */
    //@@author cletus-reused
    //Reused from https://stackoverflow.com/questions/1021324 with minor modifications
    private static boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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

    public LocalDate getLocalDate() {
        return localDateTime.toLocalDate();
    }
}
