package seedu.smartlib.model.record;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.model.record.exceptions.DuplicateRecordException;
import seedu.smartlib.model.record.exceptions.RecordNotFoundException;

/**
 * A list of records that enforces uniqueness between its elements and does not allow nulls.
 * A record is considered unique by comparing using {@code Record#isSameRecord(Record)}. As such, adding and updating of
 * records uses Record#isSameRecord(Record) for equality so as to ensure that the record being added or updated is
 * unique in terms of identity in the UniqueRecordList. However, the removal of a records uses Record#equals(Object) so
 * as to ensure that the record with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Record#isSameRecord(Record)
 */
public class UniqueRecordList implements Iterable<Record> {

    private final ObservableList<Record> internalList = FXCollections.observableArrayList();
    private final ObservableList<Record> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent record as the given argument.
     *
     * @param toCheck the record to be checked.
     * @return true if the list contains an equivalent record as the given argument, and false otherwise.
     */
    public boolean contains(Record toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameRecord);
    }

    /**
     * Adds a record to the list.
     * The record must not already exist in the list.
     *
     * @param toAdd the record to be added.
     */
    public void addRecord(Record toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateRecordException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the record {@code target} in the list with {@code editedRecord}.
     * {@code target} must exist in the list.
     * The record identity of {@code editedRecord} must not be the same as another existing record in the list.
     *
     * @param target the record to be replaced.
     * @param editedRecord the new record.
     */
    public void setRecord(Record target, Record editedRecord) {
        requireAllNonNull(target, editedRecord);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new RecordNotFoundException();
        }

        if (!target.isSameRecord(editedRecord) && contains(editedRecord)) {
            throw new DuplicateRecordException();
        }

        internalList.set(index, editedRecord);
    }

    /**
     * Removes the equivalent record from the list.
     * The record must exist in the list.
     *
     * @param toRemove the record to be removed.
     */
    public void remove(Record toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new RecordNotFoundException();
        }
    }

    /**
     * Updates the records in the unique record list.
     *
     * @param replacement the new list of records.
     */
    public void setRecords(UniqueRecordList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code records}.
     * {@code records} must not contain duplicate records.
     *
     * @param records the new list of records.
     */
    public void setRecords(List<Record> records) {
        requireAllNonNull(records);
        if (!recordsAreUnique(records)) {
            throw new DuplicateRecordException();
        }

        internalList.setAll(records);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list of records.
     */
    public ObservableList<Record> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator for the unique record list.
     *
     * @return an iterator for the unique record list.
     */
    @Override
    public Iterator<Record> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if this UniqueRecordList is equal to another UniqueRecordList.
     *
     * @param other the other UniqueRecordList to be compared.
     * @return true if this UniqueRecordList is equal to the other UniqueRecordList, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueRecordList // instanceof handles nulls
                        && internalList.equals(((UniqueRecordList) other).internalList));
    }

    /**
     * Generates a hashcode for this UniqueRecordList.
     *
     * @return the hashcode for this UniqueRecordList.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code records} contains only unique records.
     *
     * @param records the list of records to be checked.
     * @return true if {@code records} contains only unique records, and false otherwise.
     */
    private boolean recordsAreUnique(List<Record> records) {
        for (int i = 0; i < records.size() - 1; i++) {
            for (int j = i + 1; j < records.size(); j++) {
                if (records.get(i).isSameRecord(records.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
