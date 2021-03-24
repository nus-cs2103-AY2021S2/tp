package seedu.address.model.cheese.predicates;

import java.util.List;

import seedu.address.commons.util.PredicateUtil;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering cheeses by their cheese types.
 */
public class CheeseCheeseTypePredicate extends SingleFieldPredicate<Cheese> {

    public static final String MESSAGE_CONSTRAINTS = "Cheese type keywords must not be empty.";

    public CheeseCheeseTypePredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public double getSimilarityScore(Cheese cheese) {
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(cheese.getCheeseType().value, getKeywords());
    }

    @Override
    public boolean test(Cheese cheese) {
        return PredicateUtil.containsPrefixWordIgnoreCase(cheese.getCheeseType().value, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof CheeseCheeseTypePredicate) && super.equals(other); // state check
    }

}
