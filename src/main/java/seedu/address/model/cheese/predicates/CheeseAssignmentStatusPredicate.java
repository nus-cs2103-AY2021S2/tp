package seedu.address.model.cheese.predicates;

import seedu.address.model.cheese.Cheese;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Predicate for filtering cheeses by assignemnt status.
 */
public class CheeseAssignmentStatusPredicate extends FieldPredicate<Cheese> {

    private final boolean isCheeseAssigned;

    /**
     * Creates a {@code CheeseAssignmentStatusPredicate} to filter list of cheeses.
     * If input {@code isCheeseAssigned} is true, only assigned cheeses will be left in the list.
     * If input {@code isCheeseAssigned} is false, only unassigned cheeses will be left in the list.
     */
    public CheeseAssignmentStatusPredicate(boolean isCheeseAssigned) {
        super();
        this.isCheeseAssigned = isCheeseAssigned;
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
