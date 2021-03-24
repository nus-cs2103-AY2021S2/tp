package seedu.address.model.order.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;
import seedu.address.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering orders by their customers' names.
 */
public class OrderNamePredicate extends SingleFieldPredicate<Order> {

    public static final String MESSAGE_CONSTRAINTS = "Customer name keywords must not be empty.";

    private final List<Customer> customerList;

    /**
     * Creates a predicate to filter orders by the orders' customers' names.
     * The customer list is needed here to access the orders' customers' information.
     */
    public OrderNamePredicate(List<String> keywords, List<Customer> customerList) {
        super(keywords);
        this.customerList = customerList;
    }

    @Override
    public double getSimilarityScore(Order order) {
        String name = getNameByOrder(order);
        requireNonNull(name);
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(name, getKeywords());
    }

    @Override
    public boolean test(Order order) {
        String name = getNameByOrder(order);
        requireNonNull(name);
        return PredicateUtil.containsPrefixWordIgnoreCase(name, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof SingleFieldPredicate)
                && super.equals(other)
                && this.customerList.equals(((OrderNamePredicate) other).customerList);
    }

    private String getNameByOrder(Order order) {
        Customer matchingCustomer = customerList
                .stream()
                .filter(customer -> customer.getId().equals(order.getCustomerId()))
                .findAny()
                .orElse(null);
        return matchingCustomer != null ? matchingCustomer.getName().toString() : null;
    }

}
