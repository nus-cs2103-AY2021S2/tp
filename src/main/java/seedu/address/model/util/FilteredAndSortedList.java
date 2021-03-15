package seedu.address.model.util;

import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 * Equivalent to an observable list that both sorts and filters
 * based on the given predicates and comparators
 * @param <U> model class
 */
public class FilteredAndSortedList<U> {
    final FilteredList<U> filteredList;
    final SortedList<U> sortedList;

    /**
     * Creates a filtered and sorted list
     * @param list list to be sorted and filtered
     */
    public FilteredAndSortedList(ObservableList<U> list) {
        filteredList = new FilteredList<>(list);
        sortedList = new SortedList<>(filteredList);
    }

    public void setPredicate(Predicate<? super U> predicate) {
        filteredList.setPredicate(predicate);
    }

    public void setComparator(Comparator<? super U> comparator) {
        sortedList.setComparator(comparator);
    }

    public ObservableList<U> getObservableList() {
        return sortedList;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof FilteredAndSortedList // instanceof handles nulls
            && filteredList.equals(((FilteredAndSortedList) other).filteredList)
            && sortedList.equals(((FilteredAndSortedList) other).sortedList));
    }
}
