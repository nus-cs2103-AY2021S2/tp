package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.parser.exceptions.ParseException;

public class Birthday {
    public static final String MESSAGE_CONSTRAINTS =
            seedu.address.commons.util.DateUtil.MESSAGE_CONSTRAINT;

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
        try {
            DateUtil.fromDateInput(test);
            return true;
        } catch (ParseException e) {
            return false;
        }
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

