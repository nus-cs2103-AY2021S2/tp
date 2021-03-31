package seedu.smartlib.model.reader;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.smartlib.model.reader.exceptions.DuplicateReaderException;
import seedu.smartlib.model.reader.exceptions.ReaderNotFoundException;

/**
 * A list of readers that enforces uniqueness between its elements and does not allow nulls.
 * A reader is considered unique by comparing using {@code Reader#isSameReader(Reader)}. As such, adding and updating of
 * readers uses Reader#isSameREader(Reader) for equality so as to ensure that the reader being added or updated is
 * unique in terms of identity in the UniqueReaderList. However, the removal of a reader uses Reader#equals(Object) so
 * as to ensure that the reader with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Reader#isSameReader(Reader)
 */
public class UniqueReaderList implements Iterable<Reader> {

    private final ObservableList<Reader> internalList = FXCollections.observableArrayList();
    private final ObservableList<Reader> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent reader as the given argument.
     *
     * @param toCheck the reader to be checked.
     * @return true if the list contains an equivalent reader as the given argument, and false otherwise.
     */
    public boolean contains(Reader toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameReader);
    }

    /**
     * Adds a reader to the list.
     * The reader must not already exist in the list.
     *
     * @param toAdd the reader to be added.
     */
    public void addReader(Reader toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateReaderException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the reader {@code target} in the list with {@code editedReader}.
     * {@code target} must exist in the list.
     * The person identity of {@code editedReader} must not be the same as another existing reader in the list.
     *
     * @param target the reader to be replaced.
     * @param editedReader the new reader.
     */
    public void setReader(Reader target, Reader editedReader) {
        requireAllNonNull(target, editedReader);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ReaderNotFoundException();
        }

        if (!target.isSameReader(editedReader) && contains(editedReader)) {
            throw new DuplicateReaderException();
        }

        internalList.set(index, editedReader);
    }

    /**
     * Removes the equivalent reader from the list.
     * The reader must exist in the list.
     *
     * @param toRemove the reader to be removed.
     */
    public void remove(Reader toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ReaderNotFoundException();
        }
    }

    /**
     * Updates the readers in the unique reader list.
     *
     * @param replacement the new list of readers.
     */
    public void setReaders(UniqueReaderList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code readers}.
     * {@code readers} must not contain duplicate readers.
     *
     * @param readers the new list of readers.
     */
    public void setReaders(List<Reader> readers) {
        requireAllNonNull(readers);
        if (!readersAreUnique(readers)) {
            throw new DuplicateReaderException();
        }

        internalList.setAll(readers);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     *
     * @return the backing list of readers.
     */
    public ObservableList<Reader> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator for the unique reader list.
     *
     * @return an iterator for the unique reader list.
     */
    @Override
    public Iterator<Reader> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if this UniqueReaderList is equal to another UniqueReaderList.
     *
     * @param other the other UniqueReaderList to be compared.
     * @return true if this UniqueReaderList is equal to the other UniqueReaderList, and false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueReaderList // instanceof handles nulls
                        && internalList.equals(((UniqueReaderList) other).internalList));
    }

    /**
     * Generates a hashcode for this UniqueReaderList.
     *
     * @return the hashcode for this UniqueReaderList.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code readers} contains only unique readers.
     *
     * @param readers the list of readers to be checked.
     * @return true if {@code readers} contains only unique readers, and false otherwise.
     */
    private boolean readersAreUnique(List<Reader> readers) {
        for (int i = 0; i < readers.size() - 1; i++) {
            for (int j = i + 1; j < readers.size(); j++) {
                if (readers.get(i).isSameReader(readers.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
