package seedu.address.model.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Title implements Comparable<Title> {

    public static final String MESSAGE_CONSTRAINTS =
            "%s' titles should alphanumeric and should not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9 ]+$";

    public final String modTitle;

    /**
     * Constructs a {@code Title}.
     *
     * @param modTitle A valid module title.
     */
    public Title(String modTitle) {
        requireNonNull(modTitle);
        checkArgument(isValidTitle(modTitle), MESSAGE_CONSTRAINTS);
        this.modTitle = modTitle;
    }

    /**
     * Returns true if a given string is a valid module title.
     */
    public static boolean isValidTitle(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return modTitle;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Title // instanceof handles nulls
                && modTitle.equals(((Title) other).modTitle)); // state check
    }

    @Override
    public int hashCode() {
        return modTitle.hashCode();
    }

    @Override
    public int compareTo(Title other) {
        return modTitle.compareTo(other.modTitle);
    }
}
