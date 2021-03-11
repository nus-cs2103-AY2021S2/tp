package seedu.hippocampus.model.person;

import static java.util.Objects.requireNonNull;

/** Represents a Person's birthday in HippoCampus.
 * Guarantees: immutable; is always valid.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday must be in the format yyyy-mm-dd";

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
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidBirthday(String test) {
        return (test.toCharArray()[4] == '-') && (test.toCharArray()[7] == '-');
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
