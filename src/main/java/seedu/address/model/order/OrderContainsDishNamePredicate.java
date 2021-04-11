package seedu.address.model.order;

import java.util.function.Predicate;

/**
 * Tests that a Order's dish name contains this keyword
 */
public class OrderContainsDishNamePredicate implements Predicate<Order> {
    private final String keyword;

    public OrderContainsDishNamePredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Order order) {
        return order.containsDishKeyword(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OrderContainsDishNamePredicate // instanceof handles nulls
                && keyword.equals(((OrderContainsDishNamePredicate) other).keyword)); // state check
    }

}
