package chim.model.customer.predicates;

import java.util.List;

import chim.commons.util.PredicateUtil;
import chim.model.customer.Customer;
import chim.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering customers by their phone numbers.
 */
public class CustomerPhonePredicate extends SingleFieldPredicate<Customer> {

    public static final String MESSAGE_CONSTRAINTS = "Phone number keywords must be numerical and not empty.";

    public CustomerPhonePredicate(List<String> keywords) {
        super(keywords);
    }

    /**
     * Checks whether a given list of phone keywords is valid.
     */
    public static boolean isValidKeywords(List<String> keywords) {
        return SingleFieldPredicate.isValidKeywords(keywords)
                && keywords.stream().allMatch(word -> word.matches("\\d+"));
    }

    @Override
    public double getSimilarityScore(Customer customer) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(customer.getPhone().value, getKeywords());
    }

    @Override
    public boolean test(Customer customer) {
        return PredicateUtil.containsPrefixWordIgnoreCase(customer.getPhone().value, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CustomerPhonePredicate) && super.equals(other); // state check
    }

    @Override
    public String toString() {
        return String.format("phone starting with %s",
                super.toString());
    }
}
