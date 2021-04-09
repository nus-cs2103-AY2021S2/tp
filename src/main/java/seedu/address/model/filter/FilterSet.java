package seedu.address.model.filter;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * A set of predicate filters as well as a composed filter.
 */
public abstract class FilterSet<T> implements Predicate<T> {
    protected final Set<Predicate<T>> filters;
    protected Predicate<T> composedFilter;

    /**
     * Creates an empty {@code FilterSet}.
     */
    public FilterSet() {
        this.filters = new LinkedHashSet<>();
        composeFilters();
    }

    /**
     * Creates a {@code FilterSet} from {@code filters}.
     */
    public FilterSet(Set<Predicate<T>> filters) {
        this.filters = new LinkedHashSet<>(filters);
        composeFilters();
    }

    public Set<Predicate<T>> getFilters() {
        return filters;
    }

    public Predicate<T> getComposedFilter() {
        return composedFilter;
    }

    /**
     * Checks if this {@code FilterSet} contains any filters from another {@code FilterSet}.
     */
    public boolean hasAny(FilterSet<T> filterSet) {
        return !Collections.disjoint(filters, filterSet.getFilters());
    }

    /**
     * Checks if this {@code FilterSet} contains all the filters from another {@code FilterSet}.
     */
    public boolean hasAll(FilterSet<T> filterSet) {
        return filters.containsAll(filterSet.getFilters());
    }

    /**
     * Add a predicate filter to this {@code FilterSet}.
     */
    public FilterSet<T> add(Predicate<T> filter) {
        this.filters.add(filter);
        composeFilters();
        return this;
    }

    /**
     * Remove a predicate filter from this {@code FilterSet}.
     */
    public FilterSet<T> remove(Predicate<T> filter) {
        this.filters.remove(filter);
        composeFilters();
        return this;
    }

    /**
     * Add all predicate filters to this {@code FilterSet}.
     */
    public FilterSet<T> addAll(Set<Predicate<T>> filters) {
        this.filters.addAll(filters);
        composeFilters();
        return this;
    }

    /**
     * Add all predicate filters from another {@code FilterSet} to this {@code FilterSet}.
     */
    public FilterSet<T> addAll(FilterSet<T> filterSet) {
        this.filters.addAll(filterSet.getFilters());
        composeFilters();
        return this;
    }

    /**
     * Removes predicate filters from this {@code FilterSet}.
     */
    public FilterSet<T> removeAll(Set<Predicate<T>> filters) {
        this.filters.removeAll(filters);
        composeFilters();
        return this;
    }

    /**
     * Removes all predicate filters in another {@code FilterSet} from this {@code FilterSet}.
     */
    public FilterSet<T> removeAll(FilterSet<T> filterSet) {
        this.filters.removeAll(filterSet.getFilters());
        composeFilters();
        return this;
    }

    /**
     * Returns a list of strings describing the filters in this {@code FilterSet}.
     */
    public List<String> toStringList() {
        return filters.stream().map(x -> x.toString()).collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return filters.hashCode();
    }

    @Override
    public boolean test(T value) {
        return composedFilter.test(value);
    }

    /**
     * Creates a composed filter from the list of filters.
     */
    protected abstract void composeFilters();
}
