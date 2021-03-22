package seedu.address.model.customer.predicates;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.predicate.SingleFieldPredicate;

public class CustomerNamePredicate extends SingleFieldPredicate<Customer> {

    public CustomerNamePredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Customer customer) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(customer.getName().fullName, getKeywords());
    }

    @Override
    public boolean test(Customer customer) {
        return PredicateUtil.containsPrefixWordIgnoreCase(customer.getName().fullName, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CustomerNamePredicate) && super.equals(other); // state check
    }

}
