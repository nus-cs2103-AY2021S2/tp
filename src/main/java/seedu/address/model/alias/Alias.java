package seedu.address.model.alias;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Alias in the address book.
 * Guarantees: immutable; alias is valid as declared in {@link #isValidAlias(String)}
 */
public class Alias {

    public static final String MESSAGE_CONSTRAINTS =
            "Alias can take any values except spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "^\\S+$";

    public final String alias;

    /**
     * Constructs a {@code Alias}.
     *
     * @param alias A valid alias name.
     */
    public Alias(String alias) {
        requireNonNull(alias);
        checkArgument(isValidAlias(alias), MESSAGE_CONSTRAINTS);
        this.alias = alias;
    }

    /**
     * Returns true if a given string is a valid alias name.
     */
    public static boolean isValidAlias(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Alias // instanceof handles nulls
                && alias.equals(((Alias) other).alias)); // state check
    }

    @Override
    public int hashCode() {
        return alias.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        return alias;
    }

}
