package seedu.address.model.order.predicates;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.order.Order;
import seedu.address.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering orders by the cheese types assigned to them.
 */
public class OrderCheeseTypePredicate extends SingleFieldPredicate<Order> {

    public static final String MESSAGE_CONSTRAINTS = "Cheese type keywords must not be empty.";

    public OrderCheeseTypePredicate(List<String> keywordsList) {
        super(keywordsList);
    }

    @Override
    public double getSimilarityScore(Order order) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(order.getCheeseType().value, getKeywords());
    }

    @Override
    public boolean test(Order order) {
        return PredicateUtil.containsPrefixWordIgnoreCase(order.getCheeseType().value, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof OrderCheeseTypePredicate) && super.equals(other); // state check
    }

}
