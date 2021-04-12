package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable;
 */
public class Remark {

    public static final String DEFAULT_REMARK = "No remark";

    public static final String MESSAGE_CONSTRAINTS = "Remark should not contain leading and trailing spaces";

    public final String value;

    /**
     * Constructs a {@code Remark}
     *
     * @param remark If the remark is the empty string, then the default remark "No remark" will be used.
     */
    public Remark(String remark) {
        requireNonNull(remark);
        if (remark.length() == 0) {
            this.value = DEFAULT_REMARK;
        } else {
            this.value = remark;
        }
    }

    /**
     * Remark should not have any leading or trailing spaces after passing through the parser.
     */
    public static boolean isValidRemark(String test) {
        return test.trim().length() == test.length();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Remark // instanceof handles nulls
                && value.equals(((Remark) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
