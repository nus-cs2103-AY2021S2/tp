package seedu.address.model.customer.predicates;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.ModelSinglePredicate;

public class CustomerPhonePredicate extends ModelSinglePredicate<Customer> {

    public CustomerPhonePredicate(List<String> keywords) {
        super(keywords);
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

}
