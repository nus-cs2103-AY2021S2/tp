package seedu.address.model.util;

import java.util.Arrays;
import java.util.List;

/**
 * Predicate which contains multiple predicates.
 * To be used for filtering and sorting data by multiple parameters.
 * Guarantees: Immutable
 * @param <U>
 */
public class ModelCompositePredicate<U> extends ModelPredicate<U> {
    public final List<ModelPredicate<U>> modelPredicateList;

    /**
     * Initialize composite predicate with a list of predicates
     * @param l
     */
    public ModelCompositePredicate(List<ModelPredicate<U>> l) {
        assert(l.size() > 0);
        this.modelPredicateList = l;
    }

    public ModelCompositePredicate(ModelPredicate<U> modelPredicate) {
        this(Arrays.asList(modelPredicate));
    }

    @Override
    public double getSimilarityScore(U u) {
        return modelPredicateList.stream().mapToDouble(x -> x.getSimilarityScore(u)).sum();
    }

    @Override
    public boolean test(U u) {
        return modelPredicateList.stream().allMatch(x -> x.test(u));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ModelCompositePredicate // instanceof handles nulls
            && modelPredicateList.equals(((ModelCompositePredicate) other).modelPredicateList)); // state check
    }
}
