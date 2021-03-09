package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title {

    // todo change message constraints
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    // todo change regex
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String modTitle;

    /**
     * Constructs a {@code Title}.
     *
     * @param modTitle A valid module title.
     */
    public Title(String modTitle) {
        requireNonNull(modTitle);
        checkArgument(isValidName(modTitle), MESSAGE_CONSTRAINTS);
        this.modTitle = modTitle;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return modTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && modTitle.equals(((Title) other).modTitle)); // state check
    }

    @Override
    public int hashCode() {
        return modTitle.hashCode();
    }
}
