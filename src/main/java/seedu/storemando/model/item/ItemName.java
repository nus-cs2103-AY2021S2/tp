package seedu.storemando.model.item;

import static java.util.Objects.requireNonNull;
import static seedu.storemando.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's name in the storemando.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class ItemName {

    public static final String MESSAGE_CONSTRAINTS =
        "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the location must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid item name.
     */
    public ItemName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullName;
    }

    /**
     * Defines a weaker notion of equals. Returns true if 2 item names have the same spelling.
     *
     */
    public boolean isSimilar(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ItemName // instanceof handles nulls
            && fullName.equalsIgnoreCase(((ItemName) other).fullName)); // state check
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ItemName // instanceof handles nulls
            && fullName.equals(((ItemName) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    public int compare(ItemName anotherItemName) {
        return this.fullName.compareTo(anotherItemName.fullName);
    }
}
