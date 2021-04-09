package seedu.address.model.filter;

import java.util.Set;
import java.util.function.Predicate;

/**
 * A set of predicate filters as well as an inclusive composed filter.
 * Test value can match any of the filters in the set.
 */
public class InclusiveFilterSet<T> extends FilterSet<T> {
    public InclusiveFilterSet() {
        super();
    }

    public InclusiveFilterSet(Set<Predicate<T>> filters) {
        super(filters);
    }

    @Override
    protected void composeFilters() {
        // Test value can match any of the filters in the set.
        this.composedFilter = this.filters.stream()
                .reduce((x, y) -> x.or(y))
                .orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InclusiveFilterSet)) {
            return false;
        }

        InclusiveFilterSet<?> otherFilterSet = (InclusiveFilterSet<?>) other;
        return otherFilterSet.getFilters().equals(this.getFilters());
    }
}
