package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class PostalCode {
    private static final String MESSAGE_CONSTRAINTS =
            "Postal code should contain only numbers, and it should be at least 3 digits long";

    private static final String VALIDATION_REGEX = "\\d{3,}";

    public final String postal;

    public PostalCode(String postal) {
        requireNonNull(postal);
        checkArgument(isValidPostal(postal), MESSAGE_CONSTRAINTS);
        this.postal = postal;
    }

    public static boolean isValidPostal(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return postal;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PostalCode)) {
            return false;
        }
        PostalCode otherPostalCode = (PostalCode) other;
        return postal.equals(otherPostalCode.postal);
    }

    @Override
    public int hashCode() {
        return postal.hashCode();
    }
}
