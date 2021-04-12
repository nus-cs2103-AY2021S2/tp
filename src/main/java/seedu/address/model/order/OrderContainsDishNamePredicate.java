package seedu.address.model.order;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a Order's dish name contains this keyword
 */
public class OrderContainsDishNamePredicate implements Predicate<Order> {
    private final List<String> keywords;

    public OrderContainsDishNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        return order.containsDishKeywords(keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderContainsDishNamePredicate // instanceof handles nulls
                && keywords.equals(((OrderContainsDishNamePredicate) other).keywords)); // state check
    }

}
