package seedu.hippocampus.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.hippocampus.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalDate;

/** Represents a Person's birthday in HippoCampus.
 * Guarantees: immutable; is always valid.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday must be in the format yyyy-mm-dd";
    public static final String MESSAGE_YEAR_CONSTRAINTS =
            String.format("Year should not exceed %d", LocalDate.now().getYear());

    public static final String EMPTY_BIRTHDAY_STRING = "";
    public static final Birthday EMPTY_BIRTHDAY = new Birthday();

    public final String value;
    private boolean isEmpty = false;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthday(String birthdate) {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdayYear(birthdate), MESSAGE_YEAR_CONSTRAINTS);
        isValidBirthday(birthdate);
        value = birthdate;
    }

    /**
     * Constructs an empty birthday.
     */
    public Birthday() {
        value = EMPTY_BIRTHDAY_STRING;
        isEmpty = true;
    }

    /**
     * Returns true if a given birthday is an empty birthday.
     */
    public static boolean isEmptyBirthday(Birthday birthday) {
        return birthday.isEmpty;
    }

    /**
     * Returns true if a given birthday string has a valid year.
     */
    public static boolean isValidBirthdayYear(String test) {
        return Integer.parseInt(test.split("-")[0]) <= LocalDate.now().getYear();
    }

    /**
     * Throws an error if a given string is an invalid birthday.
     */
    public static void isValidBirthday(String test) {
        try {
            LocalDate.parse(test);
        } catch (DateTimeException err) {
            throw new DateTimeException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && value.equals(((Birthday) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
