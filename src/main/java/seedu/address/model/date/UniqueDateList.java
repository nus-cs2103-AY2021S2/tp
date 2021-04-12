package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.date.exceptions.DuplicateImportantDateException;
import seedu.address.model.date.exceptions.ImportantDateNotFoundException;

public class UniqueDateList implements Iterable<ImportantDate> {

    private final ObservableList<ImportantDate> internalList = FXCollections.observableArrayList();
    private final ObservableList<ImportantDate> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent important date as the given argument.
     */
    public boolean contains(ImportantDate toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameImportantDate);
    }

    /**
     * Adds an important date to the list.
     * The important date must not already exist in the list.
     */
    public void add(ImportantDate toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateImportantDateException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent important date from the list.
     * The important date must exist in the list.
     */
    public void remove(ImportantDate toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ImportantDateNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@code importantDates}.
     * {@code importantDates} must not contain duplicate important dates.
     */
    public void setImportantDates(List<ImportantDate> importantDates) {
        requireAllNonNull(importantDates);
        if (!importantDatesAreUnique(importantDates)) {
            throw new DuplicateImportantDateException();
        }

        internalList.setAll(importantDates);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ImportantDate> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ImportantDate> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDateList // instanceof handles nulls
                && internalList.equals(((UniqueDateList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code importantDates} contains only unique important dates (all dates have different
     * descriptions).
     */
    private boolean importantDatesAreUnique(List<ImportantDate> importantDates) {
        for (int i = 0; i < importantDates.size() - 1; i++) {
            for (int j = i + 1; j < importantDates.size(); j++) {
                if (importantDates.get(i).isSameImportantDate(importantDates.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
