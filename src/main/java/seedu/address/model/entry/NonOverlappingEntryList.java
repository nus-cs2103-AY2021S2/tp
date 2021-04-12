package seedu.address.model.entry;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.entry.exceptions.EntryNotFoundException;
import seedu.address.model.entry.exceptions.OverlappingEntryException;

/**
 * A list of entries that enforces that its elements do not overlap and are not nulls.
 * An entry is considered overlapping by testing using {@code Entry#overlapsWith(Entry)}.
 *
 * Supports a minimal set of list operations.
 *
 * @see Entry#overlapsWith(Entry)
 */
public class NonOverlappingEntryList implements Iterable<Entry> {

    private final ObservableList<Entry> internalList = FXCollections.observableArrayList();
    private final ObservableList<Entry> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains the same entry as the given argument.
     */
    public boolean contains(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameEntry);
    }

    /**
     * Returns true if the list contains an overlapping entry as the given argument.
     */
    public boolean overlapsWith(Entry toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::overlapsWith);
    }

    /**
     * Adds an entry to the list.
     * The entry must not overlap with existing entries in the list.
     */
    public void add(Entry toAdd) {
        requireNonNull(toAdd);

        if (overlapsWith(toAdd)) {
            throw new OverlappingEntryException();
        }

        internalList.add(toAdd);
        FXCollections.sort(internalList, new EntryComparator());
    }

    /**
     * Replaces the entry {@code target} in the list with {@code editedEntry}.
     * {@code target} must exist in the list.
     * {@code editedEntry} must not overlap with existing entries in the list.
     */
    public void setEntry(Entry target, Entry editedEntry) {
        requireAllNonNull(target, editedEntry);

        int index = internalList.indexOf(target);

        if (index == -1) {
            throw new EntryNotFoundException();
        }

        if (target.overlapsWith(editedEntry) || overlapsWith(editedEntry)) {
            throw new OverlappingEntryException();
        }

        internalList.set(index, editedEntry);
    }

    /**
     * Removes the equivalent entry from the list.
     * The entry must exist in the list.
     */
    public void remove(Entry toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new EntryNotFoundException();
        }
    }

    /**
     * Replaces the contents of this list with {@replacement}.
     * All entries in {@replacement} are non-overlapping.
     */
    public void setEntries(NonOverlappingEntryList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code entries}.
     * {@code entries} must not contain overlapping entries.
     */
    public void setEntries(List<Entry> entries) {
        requireAllNonNull(entries);
        if (!entriesAreNotOverlapping(entries)) {
            throw new OverlappingEntryException();
        }

        internalList.setAll(entries);
    }

    /**
     * Removes all overdue entries in this list.
     */
    public void clearOverdueEntries() {
        internalList.removeIf(Entry::isOverdue);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Entry> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Entry> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonOverlappingEntryList // instanceof handles nulls
                && internalList.equals(((NonOverlappingEntryList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code entries} contains only non-overlapping entries.
     */
    private boolean entriesAreNotOverlapping(List<Entry> entries) {
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                if (entries.get(i).overlapsWith(entries.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
