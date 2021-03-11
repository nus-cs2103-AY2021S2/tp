package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's birthday in the address book.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be given in the form YYYY-MM-DD, and it should not be blank";

    //public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final LocalDate birthday;

    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) throws DateTimeParseException{
        requireNonNull(birthday);
        //checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.birthday = LocalDate.parse(birthday);
    }

    /*
    /**
     * Returns true if a given string is a valid name.
     */
    /*public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }*/


    @Override
    public String toString() {
        return birthday.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Birthday // instanceof handles nulls
                && birthday.equals(((Birthday) other).birthday)); // state check
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

}
