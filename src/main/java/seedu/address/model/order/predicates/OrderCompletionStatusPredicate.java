package seedu.address.model.order.predicates;

import seedu.address.model.order.Order;
import seedu.address.model.util.predicate.FieldPredicate;

/**
 * Predicate for filtering orders by completion status.
 */
public class OrderCompletionStatusPredicate extends FieldPredicate<Order> {

    public static final String MESSAGE_CONSTRAINTS = "Input order status must be 'complete' or 'incomplete'.";

    private enum CompletionStatus {
        COMPLETE,
        INCOMPLETE
    }

    private final boolean isOrderComplete;

    /**
     * Creates a {@code OrderCompletionStatusPredicate} to filter list of orders.
     * If input {@code isOrderComplete} is 'complete', only completed will be left in the list.
     * If input {@code isOrderComplete} is 'incomplete', only uncomplete orders will be left in the list.
     */
    public OrderCompletionStatusPredicate(String status) {
        super();
        assert isValidStatus(status);
        this.isOrderComplete = status.equalsIgnoreCase(CompletionStatus.COMPLETE.toString());
    }

    /**
     * Returns if the input order completion status {@code String} is valid.
     */
    public static boolean isValidStatus(String status) {
        return status.equalsIgnoreCase(CompletionStatus.COMPLETE.toString())
                || status.equalsIgnoreCase(CompletionStatus.INCOMPLETE.toString());
    }

    @Override
    public boolean test(Order order) {
        return isOrderComplete == order.isComplete();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof OrderCompletionStatusPredicate
                && (isOrderComplete == ((OrderCompletionStatusPredicate) other).isOrderComplete));
    }

    @Override
    public double getSimilarityScore(Order order) {
        return 0;
    }

    @Override
    public int hashCode() {
        return isOrderComplete ? 1 : 0;
    }

}
