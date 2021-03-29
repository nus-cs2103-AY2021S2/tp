package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PersonType {
    public static final String MESSAGE_CONSTRAINTS =
            "PersonType should only be student or tutor, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String personType;

    /**
     * Constructs a {@code Name}.
     *
     * @param type A valid name.
     */
    public PersonType(String type) {
        requireNonNull(type);
        checkArgument(isValidPersonType(type), MESSAGE_CONSTRAINTS);
        personType = type;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidPersonType(String test) {
        if (test.matches(VALIDATION_REGEX) && (test.equals("student") || test.equals("tutor"))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isStudent() {
        return this.personType.equals("student");
    }

    public boolean isTutor() {
        return this.personType.equals("tutor");
    }

    @Override
    public String toString() {
        return personType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonType // instanceof handles nulls
                && personType.equals(((PersonType) other).personType)); // state check
    }

    @Override
    public int hashCode() {
        return personType.hashCode();
    }
}
