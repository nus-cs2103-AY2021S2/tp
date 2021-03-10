package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

// todo im not sure if this class name can be improved
// todo javadocs after fixing class name
public class OrderDescription {
    public final String value;


    public OrderDescription(String orderDescription) {
        requireNonNull(orderDescription);
        this.value = orderDescription;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.model.person.OrderDescription // instanceof handles nulls
                && value.equals(((OrderDescription) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + value + ']';
    }
}
