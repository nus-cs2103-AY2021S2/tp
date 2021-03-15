package seedu.address.model.util;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * Equivalent to an observable list that both sorts and filters
 * based on the given predicates and comparators
 * @param <U> model class
 */
public class FilteredSortedList<U> {
    final FilteredList<U> filteredList;
    final SortedList<U> sortedList;

    /**
     * Creates a filtered and sorted list
     * @param list list to be sorted and filtered
     */
    public FilteredSortedList(ObservableList<U> list) {
        filteredList = new FilteredList<>(list);
        sortedList = new SortedList<>(filteredList);
    }

    public void setModelPredicate(ModelPredicate<? super U> predicate) {
        filteredList.setPredicate(predicate);
        sortedList.setComparator(predicate);
    }

    public ObservableList<U> getObservableList() {
        return sortedList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FilteredSortedList // instanceof handles nulls
            && filteredList.equals(((FilteredSortedList) other).filteredList)
            && sortedList.equals(((FilteredSortedList) other).sortedList));
    }
}
