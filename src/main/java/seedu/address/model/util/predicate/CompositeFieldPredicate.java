package seedu.address.model.util.predicate;

import java.util.Set;

/**
 * Predicate which contains multiple predicates for fields.
 * To be used for filtering and sorting data by multiple parameters.
 * Guarantees: Immutable
 */
public class CompositeFieldPredicate<U> extends FieldPredicate<U> {

    public final Set<FieldPredicate<U>> fieldPredicateSet;

    /**
     * Initialize composite predicate with a set of predicates.
     */
    public CompositeFieldPredicate(Set<FieldPredicate<U>> fieldPredicateSet) {
        assert (fieldPredicateSet.size() > 0);
        this.fieldPredicateSet = fieldPredicateSet;
    }

    public CompositeFieldPredicate(FieldPredicate<U> fieldPredicate) {
        this(Set.of(fieldPredicate));
    }

    @Override
    public double getSimilarityScore(U u) {
        return fieldPredicateSet.stream().mapToDouble(x -> x.getSimilarityScore(u)).sum();
    }

    @Override
    public boolean test(U u) {
        return fieldPredicateSet.stream().allMatch(x -> x.test(u));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompositeFieldPredicate // instanceof handles nulls
            && fieldPredicateSet.equals(((CompositeFieldPredicate<?>) other).fieldPredicateSet)); // state check
    }

    @Override
    public int hashCode() {
        return fieldPredicateSet.size();
    }

}
