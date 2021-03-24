package seedu.address.model.cheese.predicates;

import seedu.address.model.cheese.Cheese;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Predicate for filtering cheeses by their assignemnt status.
 */
public class CheeseAssignmentStatusPredicate extends FieldPredicate<Cheese> {

    public static final String MESSAGE_CONSTRAINTS = "Input cheese status must be either 'assigned' or 'unassigned'.";

    private enum AssignmentStatus {
        ASSIGNED,
        UNASSIGNED
    }

    private final boolean isCheeseAssigned;

    /**
     * Creates a {@code CheeseAssignmentStatusPredicate} to filter list of cheeses.
     * If input {@code isCheeseAssigned} is 'assigned', only assigned cheeses will be left in the list.
     * If input {@code isCheeseAssigned} is 'unassigned', only unassigned cheeses will be left in the list.
     */
    public CheeseAssignmentStatusPredicate(String status) {
        super();
        assert isValidStatus(status);
        isCheeseAssigned = status.equalsIgnoreCase(AssignmentStatus.ASSIGNED.toString());
    }

    /**
     * Returns if the input cheese assignment status {@code String} is valid.
     */
    public static boolean isValidStatus(String status) {
        return status.equalsIgnoreCase(AssignmentStatus.ASSIGNED.toString())
                || status.equalsIgnoreCase(AssignmentStatus.UNASSIGNED.toString());
    }

    @Override
    public boolean test(Cheese cheese) {
        return isCheeseAssigned == cheese.isCheeseAssigned();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CheeseAssignmentStatusPredicate
                && (isCheeseAssigned == ((CheeseAssignmentStatusPredicate) other).isCheeseAssigned));
    }

    @Override
    public double getSimilarityScore(Cheese cheese) {
        return 0;
    }

    @Override
    public int hashCode() {
        return isCheeseAssigned ? 1 : 0;
    }

}
