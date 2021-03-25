package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's weightage (as a percentage) in the task tracker.
 */
public class Weightage implements Comparable<Weightage> {

    public static final String MESSAGE_CONSTRAINTS = "Weightage should be a percentage value greater or equal to 0%,"
            + " and lesser or equal to 100%.";

    public final Integer weightage;

    /**
     * Constructs a {@code Weightage}.
     *
     * @param weightage a valid integer representing the weightage of a task.
     */
    public Weightage(Integer weightage) {
        requireNonNull(weightage);
        checkArgument(isValidWeightage(weightage), MESSAGE_CONSTRAINTS);
        this.weightage = weightage;
    }


    /**
     * Returns true if the weightage is a value greater or equal to 0, and less or equal to 100.
     */
    public static boolean isValidWeightage(Integer weightage) {
        return weightage >= 0 && weightage <= 100;
    }

    @Override
    public String toString() {
        return String.format("%d%%", weightage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Weightage) // instanceof handles null
            && weightage.equals(((Weightage) other).weightage);
    }

    @Override
    public int hashCode() {
        return weightage.hashCode();
    }

    @Override
    public int compareTo(Weightage otherWeightage) {
        return weightage - otherWeightage.weightage;
    }
}
