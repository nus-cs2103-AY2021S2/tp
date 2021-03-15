package seedu.budgetbaby.model.month;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.budgetbaby.model.month.exception.DuplicateMonthException;
import seedu.budgetbaby.model.month.exception.MonthNotFoundException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.budgetbaby.commons.util.CollectionUtil.requireAllNonNull;

/**
 * A list of months that enforces uniqueness between its elements and does not allow nulls.
 * A month is considered unique by comparing using {@code Month#isSameMonth(Month)}. As such, adding and updating of
 * months uses Month#isSameMonth(Month) for equality so as to ensure that the month being added or updated is
 * unique in terms of identity in the UniqueMonthList. However, the removal of a month uses Month#equals(Object) so
 * as to ensure that the month with exactly the same fields will be removed.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Month#isSameMonth(Month) (Month)
 */
public class UniqueMonthList implements Iterable<Month> {

    private final ObservableList<Month> internalList = FXCollections.observableArrayList();
    private final ObservableList<Month> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent month as the given argument.
     */
    public boolean contains(Month toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameMonth);
    }

    /**
     * Adds a month to the list.
     * The month must not already exist in the list.
     */
    public void add(Month toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateMonthException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the month {@code target} in the list with {@code editedMonth}.
     * {@code target} must exist in the list.
     * The month identity of {@code editedMonth} must not be the same as another existing month in the list.
     */
    public void setMonth(Month target, Month editedMonth) {
        requireAllNonNull(target, editedMonth);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new MonthNotFoundException();
        }

        if (!target.isSameMonth(editedMonth) && contains(editedMonth)) {
            throw new DuplicateMonthException();
        }

        internalList.set(index, editedMonth);
    }

    /**
     * Removes the equivalent month from the list.
     * The month must exist in the list.
     */
    public void remove(Month toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new MonthNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Month> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Month> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof UniqueMonthList // instanceof handles nulls
            && internalList.equals(((UniqueMonthList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code months} contains only unique months.
     */
    private boolean monthsAreUnique(List<Month> months) {
        for (int i = 0; i < months.size() - 1; i++) {
            for (int j = i + 1; j < months.size(); j++) {
                if (months.get(i).isSameMonth(months.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
