package seedu.address.model.util.predicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.ListUtil.compareListWithoutOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Builder class for { @code CompositeFieldPredicate }.
 * @param <U>
 */
public class CompositeFieldPredicateBuilder<U> {
    private final List<SingleFieldPredicate<U>> singleFieldPredicatesList;

    public CompositeFieldPredicateBuilder() {
        this.singleFieldPredicatesList = new ArrayList<>();
    }

    /**
     * Simple constructor to convert single predicates into a composite predicate.
     * @param singleFieldPredicate
     * @return
     */
    public CompositeFieldPredicateBuilder<U> compose(SingleFieldPredicate<U> singleFieldPredicate) {
        requireNonNull(singleFieldPredicate);
        singleFieldPredicatesList.add(singleFieldPredicate);
        return this;
    }

    public CompositeFieldPredicate<U> build() {
        return new CompositeFieldPredicate<>(Collections.unmodifiableList(singleFieldPredicatesList));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof CompositeFieldPredicateBuilder // instanceof handles nulls
            && compareListWithoutOrder(
                singleFieldPredicatesList, (((CompositeFieldPredicateBuilder) other).singleFieldPredicatesList)
            )); // state check
    }

}
