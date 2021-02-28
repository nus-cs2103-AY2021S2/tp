package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Gender.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Gender.VALIDATION_REGEX;

public class Gender {

    public final String personGender;
    /**
     * Constructs a {@code Tag}.
     *
     * @param gender A valid gender tag.
     */

    public Gender(String gender) {
        requireNonNull(gender);
        checkArgument(isValidGender(gender), MESSAGE_CONSTRAINTS);
        this.personGender = gender;
    }

    /**
     * Returns true if a given string is a valid tag gender.
     */
    public static boolean isValidGender(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tag.Gender // instanceof handles nulls
                && personGender.equals(((seedu.address.model.tag.Gender) other).personGender)); // state check
    }
}
