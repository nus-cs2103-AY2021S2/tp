package seedu.address.model.customer.predicates;

import static seedu.address.logic.parser.ParserUtil.extractKeywordsFromEmail;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.customer.Customer;
import seedu.address.model.util.predicate.SingleFieldPredicate;

public class CustomerEmailPredicate extends SingleFieldPredicate<Customer> {

    public CustomerEmailPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Customer customer) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(
            extractKeywordsFromEmail(customer.getEmail().value),
            getKeywords()
        );
    }

    @Override
    public boolean test(Customer customer) {
        return PredicateUtil.containsPrefixWordIgnoreCase(
            extractKeywordsFromEmail(customer.getEmail().value),
            getKeywords()
        );
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CustomerEmailPredicate) && super.equals(other); // state check
    }

}
