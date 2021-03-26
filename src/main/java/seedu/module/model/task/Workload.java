package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.util.InputMismatchException;

/**
 * Enum to set workload levels of tasks.
 */
public class Workload implements Comparable<Workload> {
    public static final String LOW_WORKLOAD = "Low";
    public static final String MEDIUM_WORKLOAD = "Medium";
    public static final String HIGH_WORKLOAD = "High";

    public static final String MESSAGE_CONSTRAINTS =
            "Workload level should be an integer in range 1-3 inclusive";

    public final Integer workloadLevel;

    /**
     * Constructs a {@code Workload}.
     *
     * @param workloadLevel A valid workloadLevel.
     */
    public Workload(String workloadLevel) {
        requireNonNull(workloadLevel);
        checkArgument(isValidWorkload(workloadLevel), MESSAGE_CONSTRAINTS);
        this.workloadLevel = Integer.valueOf(workloadLevel);
    }

    public Integer getWorkloadLevel() {
        return this.workloadLevel;
    }

    /**
     * Checks if workload string can be converted to int of range 1 to 3 inclusive.
     *
     * @param workload The workload in string form.
     * @return true if above condition is fulfilled, false otherwise.
     */
    public static boolean isValidWorkload(String workload) {
        requireNonNull(workload);
        try {
            int workloadInt = Integer.valueOf(workload);
            return !(workloadInt < 1 || workloadInt > 3);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Converts workloadLevel into a string for display on Ui.
     *
     * @return String describing workload.
     */
    public String displayUi() {
        switch(workloadLevel) {
        case 1:
            return LOW_WORKLOAD;
        case 2:
            return MEDIUM_WORKLOAD;
        case 3:
            return HIGH_WORKLOAD;
        default:
            throw new InputMismatchException(MESSAGE_CONSTRAINTS);
        }
    }

    @Override
    public String toString() {
        return Integer.toString(workloadLevel);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Workload // instanceof handles nulls
                && workloadLevel.equals(((Workload) other).workloadLevel)); // state check
    }

    @Override
    public int compareTo(Workload other) {
        return -workloadLevel.compareTo(other.getWorkloadLevel());
    }

    @Override
    public int hashCode() {
        return workloadLevel.hashCode();
    }

}
