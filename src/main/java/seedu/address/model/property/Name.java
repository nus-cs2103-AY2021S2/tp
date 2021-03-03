package seedu.address.model.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Name {
    private static final String MESSAGE_CONSTRAINTS =
            "Property names should only contain alphanumeric characters and spaces, and it should not be blank";

    private static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String propertyName;

    public Name(String propertyName) {
        requireNonNull(propertyName);
        checkArgument(isValidName(propertyName), MESSAGE_CONSTRAINTS);
        this.propertyName = propertyName;
    }

    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return propertyName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Name)) {
            return false;
        }
        Name otherName = (Name) other;
        return propertyName.equals(otherName.propertyName);
    }

    @Override
    public int hashCode() {
        return propertyName.hashCode();
    }
}
