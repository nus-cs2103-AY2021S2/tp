package seedu.smartlib.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;

public class DateReturned {

    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format yyyy-mm-dd ";
    public static final String VALIDATION_REGEX = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";

    public final String value;

    /**
     * Constructs an {@code DateReturned}.
     *
     * @param date A valid date.
     */
    public DateReturned(LocalDate date) {
        requireNonNull(date);
        checkArgument(isValidDate(date.toString()), MESSAGE_CONSTRAINTS);
        value = date.toString();
    }


    /**
     * Constructs an {@code DateReturned}.
     *
     * @param date A valid date.
     */
    public DateReturned(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = date;
    }

    /**
     * Returns if a given string is a valid email.
     */
    public static boolean isValidDate(String test) {
        return test.toString().matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateReturned // instanceof handles nulls
                && value.equals(((DateReturned) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
