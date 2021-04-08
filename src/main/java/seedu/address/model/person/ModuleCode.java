package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode implements Comparable<ModuleCode> {

    public static final String MESSAGE_CONSTRAINTS =
        "Codes should begin with uppercase CS, have 4 digits, end optionally with a "
            + "uppercase character, and it should not be blank";

    public static final String VALIDATION_REGEX = "[C][S][1-6]\\d{3}[A-Z]?";

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

    @Override
    public int compareTo(ModuleCode other) {
        return moduleCode.compareTo(other.moduleCode);
    }

}
