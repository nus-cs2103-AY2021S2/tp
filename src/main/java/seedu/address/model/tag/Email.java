package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Name.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Name.VALIDATION_REGEX;

public class Email {

    public final String email;
    /**
     * Constructs a {@code Tag}.
     *
     * @param email A valid email tag.
     */

    public Email(String email) {
        requireNonNull(email);
        checkArgument(isValidName(email), MESSAGE_CONSTRAINTS);
        this.email = email;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tag.Email // instanceof handles nulls
                && email.equals(((seedu.address.model.tag.Email) other).email)); // state check
    }
}
