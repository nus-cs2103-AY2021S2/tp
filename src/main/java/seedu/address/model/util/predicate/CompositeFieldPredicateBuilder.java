package seedu.address.model.util.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Builder class for { @code CompositeFieldPredicate }.
 * @param <U>
 */
public class CompositeFieldPredicateBuilder<U> {
    private final Set<SingleFieldPredicate<U>> singleFieldPredicatesSet;

    public CompositeFieldPredicateBuilder() {
        this.singleFieldPredicatesSet = new HashSet<>();
    }

    /**
     * Simple constructor to convert single predicates into a composite predicate.
     * @param singleFieldPredicate
     * @return
     */
    public CompositeFieldPredicateBuilder<U> compose(SingleFieldPredicate<U> singleFieldPredicate) {
        requireNonNull(singleFieldPredicate);
        singleFieldPredicatesSet.add(singleFieldPredicate);
        return this;
    }

    public CompositeFieldPredicate<U> build() {
        return new CompositeFieldPredicate<U>(Collections.unmodifiableSet(singleFieldPredicatesSet));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompositeFieldPredicateBuilder // instanceof handles nulls
            && singleFieldPredicatesSet.equals(((CompositeFieldPredicateBuilder<?>) other).singleFieldPredicatesSet));
    }

}
