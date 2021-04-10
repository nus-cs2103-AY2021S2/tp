package chim.model.customer.predicates;

import static chim.commons.util.StringUtil.replaceEmailSpecialCharacters;

import java.util.List;

import chim.commons.util.PredicateUtil;
import chim.model.customer.Customer;
import chim.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering customers by their emails.
 */
public class CustomerEmailPredicate extends SingleFieldPredicate<Customer> {

    public static final String MESSAGE_CONSTRAINTS = "Email keywords must not be empty.";

    public CustomerEmailPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Customer customer) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(
            replaceEmailSpecialCharacters(customer.getEmail().value),
            getKeywords()
        );
    }

    @Override
    public boolean test(Customer customer) {
        return PredicateUtil.containsPrefixWordIgnoreCase(
            replaceEmailSpecialCharacters(customer.getEmail().value),
            getKeywords()
        );
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CustomerEmailPredicate) && super.equals(other); // state check
    }

    @Override
    public String toString() {
        return String.format("email starting with %s",
                super.toString());
    }
}
