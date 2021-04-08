package chim.model.customer.predicates;

import java.util.List;

import chim.commons.util.PredicateUtil;
import chim.model.customer.Customer;
import chim.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering customers by their addresses.
 */
public class CustomerAddressPredicate extends SingleFieldPredicate<Customer> {

    public static final String MESSAGE_CONSTRAINTS = "Address keywords must not be empty.";

    public CustomerAddressPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Customer customer) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(customer.getAddress().value, getKeywords());
    }

    @Override
    public boolean test(Customer customer) {
        return PredicateUtil.containsPrefixWordIgnoreCase(customer.getAddress().value, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CustomerAddressPredicate) && super.equals(other); // state check
    }

    @Override
    public String toString() {
        return String.format("address containing keyword(s) of %s",
                super.toString());
    }

}
