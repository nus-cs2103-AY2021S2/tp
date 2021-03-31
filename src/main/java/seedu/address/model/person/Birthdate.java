package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthdate in the address book.
 */
public class Birthdate {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdate should be given in the form YYYY-MM-DD and be between 1900-01-01 and "
                    + "the current date. It should not be blank";

    public final LocalDate value;

    /**
     * Constructs a {@code Birthdate}.
     *
     * @param birthdate A valid birthdate.
     */
    public Birthdate(String birthdate) throws DateTimeParseException {
        requireNonNull(birthdate);
        checkArgument(isValidBirthdate(birthdate), MESSAGE_CONSTRAINTS);
        this.value = LocalDate.parse(birthdate);
        assert this.value instanceof LocalDate : "Birthdate should be a LocalDate object";
    }

    /**
     * Returns true if a given string is a valid birthdate.
     */
    public static boolean isValidBirthdate (String test) {
        try {
            LocalDate.parse(test);
            return isValidBirthdate(LocalDate.parse(test));
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isValidBirthdate (LocalDate test) {
        return test.isBefore(LocalDate.now()) && test.isAfter(LocalDate.parse("1900-01-01"));
    }


    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthdate // instanceof handles nulls
                && value.equals(((Birthdate) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
