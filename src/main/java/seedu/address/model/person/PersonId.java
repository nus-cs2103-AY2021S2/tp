package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

public class PersonId {

    public static final String MESSAGE_CONSTRAINTS =
            "PersonId should only be s/[ID] or t/[ID], and cannot be blank.";

    /*
     * The first character must be s or t, followed by /,
     * and followed by numbers with at least 1 digit.
     */
    public static final String VALIDATION_REGEX = "[st][/]\\d{1,}";

    public final String personId;

    /**
     * Constructs a {@code PersonId}.
     *
     * @param id A valid ID.
     */
    public PersonId(String id) {
        requireNonNull(id);
        personId = id;
    }

    /**
     * Checks if Person ID is in the correct format.
     *
     * @param test Person ID to be checked.
     * @return True if Person ID is in the correct format.
     */
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
