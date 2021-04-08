package chim.model.cheese.predicates;

import java.util.List;
import java.util.stream.Collectors;

import chim.commons.util.PredicateUtil;
import chim.model.cheese.Cheese;
import chim.model.util.predicate.SingleFieldPredicate;

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

    @Override
    public String toString() {
        return String.format("cheese type keyword(s) of %s",
                keywords.stream().collect(Collectors.joining(" , ")));
    }
}
