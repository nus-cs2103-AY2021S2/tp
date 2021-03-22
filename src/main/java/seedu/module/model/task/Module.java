package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.util.List;

import seedu.module.model.ModuleManager;


/**
 * Represents a Module in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleFormat(String)}
 */
public class Module {

    public static final String MESSAGE_CONSTRAINTS = "Modules should be formatted as {MAJOR_NAME}{MODULE_CODE}{IS_PLUS}"
            + "and adhere to the following constraints:\n"
            + "1. The Module must be supported. \n"
            + "2. The {MAJOR_NAME} should only contain 2 or 3 capital letter.\n"
            + "3. This is followed by {MODULE_CODE}. "
            + "The MODULE_CODE must be made up of four digits.\n"
            + "{IS_PLUS} must be one character S, or nothing.";

    public static final String MESSAGE_MODULE_NOT_SUPPORTED = "Module code you entered is not supported";

    private static final String MAJOR_REGEX = "[A-Z]{2,3}"; // only 2 or 3 uppercase characters
    private static final String MODULE_CODE_REGEX = "\\d{4}"; // alphanumeric, period and hyphen
    private static final String LAST_CHARACTER_REGEX = "[A-Z]?";
    public static final String VALIDATION_REGEX = MAJOR_REGEX + MODULE_CODE_REGEX + LAST_CHARACTER_REGEX;
    public final String value;

    /**
     * Constructs an {@code Module}.
     * Cannot be simply used, must only be used by administrator to create new Modules.
     *
     * @param module A valid module description.
     */
    public Module(String module) {
        requireNonNull(module);
        checkArgument(isValidModuleFormat(module), MESSAGE_CONSTRAINTS);
        checkArgument(isSupportedModuleCode(module), MESSAGE_MODULE_NOT_SUPPORTED);
        value = module;
    }

    /**
     * Returns if a given string is a valid module that matches the format.
     */
    public static boolean isValidModuleFormat(String test) {
        boolean correctFormat = test.matches(VALIDATION_REGEX);
        return correctFormat;
    }

    /**
     * Returns if a given string is a module which is supported by ModuleBook.
     */
    public static boolean isSupportedModuleCode(String test) {
        ModuleManager.initSupportedModulesInStr();
        List<String> existingModules = ModuleManager.getListOfExistingModules();
        return existingModules.contains(test);
    }

    /**
     * Returns a list of existing Modules
     *
     * @return List of existing Modules
     */

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
