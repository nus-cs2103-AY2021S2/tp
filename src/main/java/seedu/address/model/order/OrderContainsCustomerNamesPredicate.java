package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that an order contains customer name that has these keywords
 */
public class OrderContainsCustomerNamesPredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderContainsCustomerNamesPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return keywords.stream()
                .anyMatch(order::containsCustomerKeyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderContainsCustomerNamesPredicate // instanceof handles nulls
                && keywords.equals(((OrderContainsCustomerNamesPredicate) other).keywords)); // state check
    }

}
