package seedu.address.model.property;

import java.util.function.Predicate;

/**
 * Tests that a {@code Property}'s {@code Deadline} matches the deadline given.
 */
public class PropertyDeadlinePredicate implements Predicate<Property> {
    private final Deadline deadline;

    public PropertyDeadlinePredicate (Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean test(Property property) {
        return deadline.equals(property.getDeadline());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PropertyDeadlinePredicate // instanceof handles nulls
                && deadline.equals(((PropertyDeadlinePredicate) other).deadline)); // state check
    }

}
