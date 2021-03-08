package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

// todo im not sure if this class name can be improved
// todo javadocs after fixing class name
public class OrderDescription {
    public final String orderDescription;

    public OrderDescription(String orderDescription) {
        requireNonNull(orderDescription);
        this.orderDescription = orderDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.OrderDescription // instanceof handles nulls
                && orderDescription.equals(((OrderDescription) other).orderDescription)); // state check
    }

    @Override
    public int hashCode() {
        return orderDescription.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + orderDescription + ']';
    }
}
