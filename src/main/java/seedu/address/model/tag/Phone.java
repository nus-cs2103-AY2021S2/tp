package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.person.Name.MESSAGE_CONSTRAINTS;
import static seedu.address.model.person.Name.VALIDATION_REGEX;

public class Phone {

    public final String PhoneNumber;
    /**
     * Constructs a {@code Tag}.
     *
     * @param PhoneNumber A valid PhoneNumber tag.
     */

    public Phone(String PhoneNumber) {
        requireNonNull(PhoneNumber);
        checkArgument(isValidPhoneNumber(PhoneNumber), MESSAGE_CONSTRAINTS);
        this.PhoneNumber = PhoneNumber;
    }

    /**
     * Returns true if a given string is a valid PhoneNumber tag.
     */
    public static boolean isValidPhoneNumber(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.tag.Phone // instanceof handles nulls
                && PhoneNumber.equals(((seedu.address.model.tag.Phone) other).PhoneNumber)); // state check
    }
}
