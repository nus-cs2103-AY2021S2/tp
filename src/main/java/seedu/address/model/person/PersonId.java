package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PersonId {

    public static final String MESSAGE_CONSTRAINTS =
            "PersonId should only be s/[ID] or t/[ID], and cannot be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[st][/]\\d";

    public final String personId;

    /**
     * Constructs a {@code Name}.
     *
     * @param id A valid ID.
     */
    public PersonId(String id) {
        requireNonNull(id);
        personId = id;
    }

    public static boolean isValidPersonId(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return personId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonId // instanceof handles nulls
                && personId.equals(((PersonId) other).personId)); // state check
    }

    @Override
    public int hashCode() {
        return personId.hashCode();
    }
}
