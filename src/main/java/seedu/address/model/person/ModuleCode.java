package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    public static final String MESSAGE_CONSTRAINTS =
        "Codes should begin with CS, end with 4 digits, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[C][S]\\d{4}";

    public final String moduleCode;

    /**
     * Constructs a {@code ModuleCode}.
     *
     * @param code A valid module code.
     */
    public ModuleCode(String code) {
        requireNonNull(code);
        checkArgument(isValidModuleCode(code), MESSAGE_CONSTRAINTS);
        moduleCode = code;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return moduleCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModuleCode // instanceof handles nulls
            && moduleCode.equals(((ModuleCode) other).moduleCode)); // state check
    }

    @Override
    public int hashCode() {
        return moduleCode.hashCode();
    }

}
