package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.util.InputMismatchException;
import java.util.List;

import seedu.module.model.ModuleManager;


/**
 * Represents a Module in the module book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleFormat(String)}
 */
public class Module implements Comparable<Module> {

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
    private int lowWorkloadCount = 0;
    private int mediumWorkloadCount = 0;
    private int highWorkloadCount = 0;

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
     * Returns a count of the number of tasks of each workload rating.
     */
    public String getWorkloadCount() {
        return String.format("Low workload tasks: %d, medium workload tasks: %d, high workload tasks %d\n",
                lowWorkloadCount, mediumWorkloadCount, highWorkloadCount);
    }

    /**
     * Increases count of number of tasks in the relevant workload rating.
     */
    public void incrementWorkload(Workload workload) {
        requireNonNull(workload);
        assert !(workload.getWorkloadLevel() < 1 || workload.getWorkloadLevel() > 3);
        switch (workload.getWorkloadLevel()) {
        case 1:
            lowWorkloadCount++;
            break;
        case 2:
            mediumWorkloadCount++;
            break;
        case 3:
            highWorkloadCount++;
            break;
        default:
            throw new InputMismatchException("Illegal workload level detected!");
        }
    }

    /**
     * Decreases count of number of tasks in the relevant workload rating.
     */
    public void decrementWorkload(Workload workload) {
        requireNonNull(workload);
        assert !(workload.getWorkloadLevel() < 1 || workload.getWorkloadLevel() > 3);
        switch (workload.getWorkloadLevel()) {
        case 1:
            assert lowWorkloadCount - 1 >= 0;
            lowWorkloadCount--;
            break;
        case 2:
            assert mediumWorkloadCount - 1 >= 0;
            mediumWorkloadCount--;
            break;
        case 3:
            assert highWorkloadCount - 1 >= 0;
            highWorkloadCount--;
            break;
        default:
            throw new InputMismatchException("Illegal workload level detected!");
        }
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

    @Override
    public int compareTo(Module o) {
        return -this.toString().compareTo(o.toString());
    }
}
