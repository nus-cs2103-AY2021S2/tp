package seedu.address.model.groupmate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Role.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRoleName(String)}
 */
public class Role {

    public static final String MESSAGE_CONSTRAINTS =
            "Role names should be alphanumeric and at least 2-character long"
            + " (can have underscores and hyphens in the middle)";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9][a-zA-Z0-9_-]*[a-zA-Z0-9]$";

    public final String roleName;

    /**
     * Constructs a {@code Role}.
     *
     * @param roleName A valid role name.
     */
    public Role(String roleName) {
        requireNonNull(roleName);
        checkArgument(isValidRoleName(roleName), MESSAGE_CONSTRAINTS);
        this.roleName = roleName;
    }

    /**
     * Returns true if a given string is a valid role name.
     */
    public static boolean isValidRoleName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && roleName.equals(((Role) other).roleName)); // state check
    }

    @Override
    public int hashCode() {
        return roleName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + roleName + ']';
    }

}
