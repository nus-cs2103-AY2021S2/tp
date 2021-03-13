package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.util.InputMismatchException;

/**
 * Enum to set workload levels of tasks.
 */
public class Workload {
    public static final String MESSAGE_CONSTRAINTS =
            "Workload level should be an integer in range 1-3 inclusive";

    private final int workloadLevel;

    public Workload(int workloadLevel) {
        requireNonNull(workloadLevel);
        checkArgument(isValidWorkload(workloadLevel), MESSAGE_CONSTRAINTS);
        this.workloadLevel = workloadLevel;
    }

    public static boolean isValidWorkload(int workloadLevel) {
        requireNonNull(workloadLevel);
        return !(workloadLevel < 1 || workloadLevel > 3);
    }

    @Override
    public String toString() {
        switch(workloadLevel) {
        case 1:
            return "Low";
        case 2:
            return "Medium";
        case 3:
            return "High";
        default:
            throw new InputMismatchException(MESSAGE_CONSTRAINTS);
        }
    }

}
