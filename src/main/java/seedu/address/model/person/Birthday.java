package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should be in the form DD-MM-YYYY, and it should not be blank";

    public static final String VALIDATION_REGEX = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$";

    public final String birthday;

    /**
     * Constructs a {@code Birthday}
     *
     * @param birthday A non-empty birthday
     */
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.birthday = birthday;
    }

    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return birthday;
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

