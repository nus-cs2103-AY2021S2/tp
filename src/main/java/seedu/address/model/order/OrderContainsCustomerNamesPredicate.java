package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
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
