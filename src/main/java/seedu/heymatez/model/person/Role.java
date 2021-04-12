package seedu.heymatez.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.heymatez.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's role in Hey Matez.
 * Guarantees: immutable; is valid as declared in {@link #isValidRole(String)}
 */
public class Role {
    public static final String MESSAGE_CONSTRAINTS =
            "Member roles can only take any alphanumeric characters, and it should not be blank ";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String memberRole;

    /**
     * Constructs a {@code Role}.
     *
     * @param role A valid member role as a string.
     */
    public Role(String role) {
        requireNonNull(role);
        checkArgument(isValidRole(role), MESSAGE_CONSTRAINTS);
        memberRole = role;
    }

    /**
     * Returns true if a given string is a valid member role.
     *
     * @return a boolean value
     */
    public static boolean isValidRole(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return memberRole;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Role // instanceof handles nulls
                && memberRole.equals(((Role) other).memberRole)); // state check
    }

    @Override
    public int hashCode() {
        return memberRole.hashCode();
    }
}

