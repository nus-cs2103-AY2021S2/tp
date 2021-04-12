package seedu.address.model.task.attributes;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.model.task.ValidDateFormatter;


/**
 * Represents a Task's date in the planner.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date implements Attribute {
    public static final String FIELD_NAME = "Date";

    public static final String MESSAGE_CONSTRAINTS =
            "Date should be in the format dd/mm/yyyy. For example, 1 July 2021 should be "
                    + "expressed as 01/07/2021, not 1/7/2021."
                    + "\n- Day, month or year should not be zeros."
                    + "\n- Date cannot be before year 1900 or after 2099.";

    public static final String VALIDATION_REGEX = "^((0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(19|20)\\d\\d)$";

    private static final Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public final LocalDate value;
    public final boolean isOverdued;
    private boolean isValidDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date number.
     */
    public Date(String date) {
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = parseDate(date);
        isValidDate = ValidDateFormatter.isValid(date);

        if (date.length() != 0) {
            LocalDate today = LocalDate.now();
            LocalDate parsedDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (parsedDate.isAfter(today)) {
                isOverdued = false;
            } else {
                isOverdued = true;
            }
        } else {
            isOverdued = false;
        }
    }

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid LocalDate object.
     */
    public Date(LocalDate date) {
        value = date;
        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            isOverdued = false;
        } else {
            isOverdued = true;
        }
    }

    /**
     * Returns true if a given string is a valid date number.
     */
    public static boolean isValidDate(String test) {
        Pattern p = Pattern.compile(VALIDATION_REGEX);
        Matcher m = p.matcher(test);

        return m.matches() || test.isEmpty();

    }

    /**
     * Used to check whether the input date is valid
     *  A input date is considered valid if months of Feb does not have more than 28 days except leap years and
     *  Days did not exceed for months of 30 days (Apr, Jun, Nov, Sep) and 31 days (Jan, Mar, May, Jul, Aug, Oct, Dec)
     *
     * @return Boolean result of whether the input Date is valid
     */
    public boolean isInvalidDate() {
        return !isValidDate && !isEmptyValue();
    }

    /**
     * Returns a date in the form of a LocalDate.
     * @param date the specified date.
     * @return
     */
    public static LocalDate parseDate(String date) {

        if (date.isEmpty()) {
            return null;
        } else {
            LocalDate parsedDate = LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return parsedDate;
        }
    }

    public LocalDate getDate() {
        LOGGER.log(Level.INFO, "Getting Date");
        return value;
    }

    /**
     * Indicates whether the date is already over
     * @return boolean to indicate whether date is over
     */
    public boolean over() {
        LOGGER.log(Level.INFO, "Checking if the date is after today");
        LocalDate now = LocalDate.now();
        return now.isAfter(value);
    }

    /**
     * Returns true if the task has a date that is within seven days from the current system date.
     * @param currentDate current date of the system
     * @return true if task's date is within seven days from current system date.
     */
    public boolean isWithinSevenDays(LocalDate currentDate) {
        LocalDate sevenDaysFromNow = currentDate.plusDays(7);

        return value.isBefore(sevenDaysFromNow);
    }

    /**
     * Checks if there is a value present.
     *
     * @return true is value is null, false otherwise.
     */
    public boolean isEmptyValue() {
        return value == null;
    }

    @Override
    public String toString() {
        if (value != null) {
            return value.format(
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && ((value == null && ((Date) other).value == null)
                        || value.equals(((Date) other).value))); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
