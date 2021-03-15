package seedu.address.model.order.predicates;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.order.Order;
import seedu.address.model.util.ModelSinglePredicate;

public class OrderIdPredicate extends ModelSinglePredicate<Order> {

    public OrderIdPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Order order) {
        return -order.getOrderId().value;
    }

    @Override
    public boolean test(Order order) {
        return PredicateUtil.matchIntegerId(order.getOrderId().value, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof OrderIdPredicate) && super.equals(other); // state check
    }

}
