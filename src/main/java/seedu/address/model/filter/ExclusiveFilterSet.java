package seedu.address.model.filter;

import java.util.Set;
import java.util.function.Predicate;

/**
 * A set of predicate filters as well as an exclusive composed filter.
 * Test value must match all of the filters in the set.
 */
public class ExclusiveFilterSet<T> extends FilterSet<T> {
    public ExclusiveFilterSet() {
        super();
    }

    public ExclusiveFilterSet(Set<Predicate<T>> filters) {
        super(filters);
    }

    @Override
    protected void composeFilters() {
        // Test value must match all of the filters in the set
        this.composedFilter = this.filters.stream()
                .reduce((x, y) -> x.and(y))
                .orElse(x -> true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExclusiveFilterSet)) {
            return false;
        }

        ExclusiveFilterSet<?> otherFilterSet = (ExclusiveFilterSet<?>) other;
        return otherFilterSet.getFilters().equals(this.getFilters());
    }
}
