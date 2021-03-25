package seedu.address.model.sort.descriptor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a value.
 * Guarantees: immutable; is valid as declared in {@link #isValidPropertySortingKey(String)}.
 */
public class PropertySortingKey {
    public static final String MESSAGE_CONSTRAINTS = "PropertySortingKey can take value of name, price,"
            + " postalcode, address, or deadline, and it should not be any other values";

    private static final String VALIDATION_REGEX = "name|price|postalcode|address|deadline";

    private static final String PRICE_STRING = "price";

    private static final String NAME_STRING = "name";

    private static final String POSTAL_CODE_STRING = "postalcode";

    private static final String DEADLINE_STRING = "deadline";

    private static final String ADDRESS_STRING = "address";

    public final String value;

    /**
     * Constructs a {@code PropertySortingKey}.
     *
     * @param value A valid value representing an PropertySortingKey.
     */
    public PropertySortingKey(String value) {
        requireNonNull(value);
        checkArgument(isValidPropertySortingKey(value), MESSAGE_CONSTRAINTS);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid value.
     *
     * @param test The string to test.
     * @return True if the given string is a valid value, otherwise false.
     */
    public static boolean isValidPropertySortingKey(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Tests whether the sorting key is deadline.
     *
     * @return True if the sorting key is deadline, otherwise false.
     */
    public boolean isDeadline() {
        return this.value.equals(DEADLINE_STRING);
    }

    /**
     * Tests whether the sorting key is postalcode.
     *
     * @return True if the sorting key is postalcode, otherwise false.
     */
    public boolean isPostalCode() {
        return this.value.equals(POSTAL_CODE_STRING);
    }

    /**
     * Tests whether the sorting key is price.
     *
     * @return True if the sorting key is price, otherwise false.
     */
    public boolean isPrice() {
        return this.value.equals(PRICE_STRING);
    }

    /**
     * Tests whether the sorting key is name.
     *
     * @return True if the sorting key is name, otherwise false.
     */
    public boolean isName() {
        return this.value.equals(NAME_STRING);
    }

    /**
     * Tests whether the sorting key is address.
     *
     * @return True if the sorting key is address, otherwise false.
     */
    public boolean isAddress() {
        return this.value.equals(ADDRESS_STRING);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PropertySortingKey)) {
            return false;
        }
        PropertySortingKey otherPropertySortingKey = (PropertySortingKey) other;
        return value.equals(otherPropertySortingKey.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
