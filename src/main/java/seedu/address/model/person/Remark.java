package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Person's remark in the address book.
 * Guarantees: immutable;
 */
public class Remark {

    public static final String DEFAULT_REMARK = "No remark";

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
