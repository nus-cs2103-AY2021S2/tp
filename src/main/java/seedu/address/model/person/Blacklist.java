package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's blacklist status in the address book.
 * Guarantees: immutable; is always valid
 */
public class Blacklist {
    public final Boolean isBlacklisted;

    /**
     * Constructs an {@code Blacklist}.
     *
     * @param isBlacklisted A valid boolean.
     */
    public Blacklist(Boolean isBlacklisted) {
        requireNonNull(isBlacklisted);
        this.isBlacklisted = isBlacklisted;
    }

    /**
     * Constructs an {@code Blacklist} with default input of false.
     */
    public Blacklist() {
        this.isBlacklisted = false;
    }

    @Override
    public String toString() {
        if (isBlacklisted) {
            return "Blacklisted";
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Blacklist // instanceof handles nulls
                && isBlacklisted.equals(((Blacklist) other).isBlacklisted)); // state check
    }

    @Override
    public int hashCode() {
        return isBlacklisted.hashCode();
    }
}
