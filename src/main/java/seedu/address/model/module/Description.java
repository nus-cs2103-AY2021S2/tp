package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Description {
    // todo change message constraints
    public static final String MESSAGE_CONSTRAINTS =
            "Description should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // todo change regex
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String description;

    /**
     * Constructs a {@code Description}.
     *
     * @param description A valid assignment description.
     */
    public Description(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    public static Description defaultDescription() {
        return new Description("default");
    }

    /**
     * Returns true if a given string is a valid assignment description.
     */
    public static boolean isValidDescription(String description) {
        return description.matches(VALIDATION_REGEX);
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
