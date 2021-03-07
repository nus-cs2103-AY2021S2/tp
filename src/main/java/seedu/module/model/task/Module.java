package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's module in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModule(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be of the format {MAJOR_NAME}{MODULE_CODE}{IS_PLUS}"
            + "and adhere to the following constraints:\n"
            + "1. The {MAJOR_NAME} should only contain 2 or 3 capital letter.\n"
            + "2. This is followed by {MODULE_CODE}. "
            + "The MODULE_CODE must be made up of four digits.\n"
            + "{IS_PLUS} must be one character S, or nothing.";
    private static final String MAJOR_REGEX = "[A-Z]{2,3}"; // only 2 or 3 uppercase characters
    private static final String MODULE_CODE_REGEX = "\\d{4}"; // alphanumeric, period and hyphen
    private static final String LAST_CHARACTER_REGEX = "\\w?";
    public static final String VALIDATION_REGEX = MAJOR_REGEX + MODULE_CODE_REGEX + LAST_CHARACTER_REGEX;


    public final String value;

    /**
     * Constructs an {@code Module}.
     *
     * @param module A valid module description.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModule(module), MESSAGE_CONSTRAINTS);
        value = module;

    }

    /**
     * Returns if a given string is a valid module.
     */
    public static boolean isValidModule(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Module // instanceof handles nulls
                && value.equals(((Module) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
