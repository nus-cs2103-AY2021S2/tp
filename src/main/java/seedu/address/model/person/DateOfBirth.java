package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Represents a Patient's date of birth in DocBob.
 * Guarantees: immutable; is valid if date of birth is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of Birth must be in the form DDMMYYYY, "
                                                    + "be in the past, "
                                                    + "and be a valid date.";

    public final String value;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        checkArgument(isValidDateOfBirth(dateOfBirth), MESSAGE_CONSTRAINTS);
        this.value = dateOfBirth;
    }

    /**
     * Returns true if a given date is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        if (test.length() != 8) { //Return false if not in DDMMYYYY format
            return false;
        }
        LocalDate date;
        try { //If any exception is thrown here date is not in correct format
            int day = Integer.parseInt(test.substring(0, 2));
            int month = Integer.parseInt(test.substring(2, 4));
            int year = Integer.parseInt(test.substring(4, 8));
            date = LocalDate.of(year, month, day);
        } catch (NumberFormatException | DateTimeException e) {
            return false;
        }
        if (date.isAfter(LocalDate.now())) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return value.substring(0, 2) + "-" + value.substring(2, 4) + "-" + value.substring(4, 8);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && value.equals(((DateOfBirth) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
