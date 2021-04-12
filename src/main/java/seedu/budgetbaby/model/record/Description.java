package seedu.budgetbaby.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.AppUtil.checkArgument;

/**
 * Represents a Financial record's description in the budget tracker.
 * Guarantees: is valid as declared in {@link #isValidDescription(String)}
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
        "Financial record description should not be blank. "
            + "It should contain less than 100 characters (including space) in total "
            + "and it should not contain special character \"/\".";
    public static final String VALIDATION_REGEX = "^((?!/).)*$";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Returns true if a given string is a valid description (not blank).
     */
    public static boolean isValidDescription(String test) {
        return !test.isBlank() && test.matches(VALIDATION_REGEX) && test.length() <= 100;
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Description // instanceof handles nulls
            && description.equals(((Description) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

}
