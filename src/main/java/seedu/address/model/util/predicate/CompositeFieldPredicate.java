package seedu.address.model.util.predicate;

import static seedu.address.commons.util.ListUtil.compareListWithoutOrder;

import java.util.Arrays;
import java.util.List;

/**
 * Predicate which contains multiple predicates for fields.
 * To be used for filtering and sorting data by multiple parameters.
 * Guarantees: Immutable
 * @param <U>
 */
public class CompositeFieldPredicate<U> extends FieldPredicate<U> {
    private final List<FieldPredicate<U>> fieldPredicateList;

    /**
     * Initialize composite predicate with a list of predicates
     * @param fieldPredicateList
     */
    public CompositeFieldPredicate(List<FieldPredicate<U>> fieldPredicateList) {
        assert(fieldPredicateList.size() > 0);
        this.fieldPredicateList = fieldPredicateList;
    }

    public CompositeFieldPredicate(FieldPredicate<U> fieldPredicate) {
        this(Arrays.asList(fieldPredicate));
    }

    @Override
    public double getSimilarityScore(U u) {
        return fieldPredicateList.stream().mapToDouble(x -> x.getSimilarityScore(u)).sum();
    }

    @Override
    public boolean test(U u) {
        return fieldPredicateList.stream().allMatch(x -> x.test(u));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompositeFieldPredicate // instanceof handles nulls
            && compareListWithoutOrder(
                fieldPredicateList, ((CompositeFieldPredicate) other).fieldPredicateList
            )); // state check
    }
}
