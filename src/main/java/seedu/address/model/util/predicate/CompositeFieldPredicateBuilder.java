package seedu.address.model.util.predicate;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Builder class for {@code CompositeFieldPredicate}.
 */
public class CompositeFieldPredicateBuilder<U> {

    private final Set<FieldPredicate<U>> fieldPredicatesSet;

    public CompositeFieldPredicateBuilder() {
        this.fieldPredicatesSet = new HashSet<>();
    }

    /**
     * Simple constructor to convert single predicates into a composite predicate.
     */
    public CompositeFieldPredicateBuilder<U> compose(FieldPredicate<U> fieldPredicate) {
        requireNonNull(fieldPredicate);
        fieldPredicatesSet.add(fieldPredicate);
        return this;
    }

    public CompositeFieldPredicate<U> build() {
        return new CompositeFieldPredicate<U>(Collections.unmodifiableSet(fieldPredicatesSet));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompositeFieldPredicateBuilder // instanceof handles nulls
            && fieldPredicatesSet.equals(((CompositeFieldPredicateBuilder<?>) other).fieldPredicatesSet));
    }

}
