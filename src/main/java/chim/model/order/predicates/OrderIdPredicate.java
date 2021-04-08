package chim.model.order.predicates;

import java.util.List;

import chim.commons.util.PredicateUtil;
import chim.model.order.Order;
import chim.model.util.predicate.SingleFieldPredicate;

public class OrderIdPredicate extends SingleFieldPredicate<Order> {

    public OrderIdPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Order order) {
        return -order.getOrderId().value;
    }

    @Override
    public boolean test(Order order) {
        return PredicateUtil.matchIntegerId(order.getOrderId(), getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof OrderIdPredicate) && super.equals(other); // state check
    }

    @Override
    public String toString() {
        return String.format("order id of %s",
                super.toString());
    }
}
