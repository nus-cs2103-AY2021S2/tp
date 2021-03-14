package seedu.dictionote.model.dictionary;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.dictionote.model.contact.exceptions.DuplicateContactException;

/**
 * A list of notes that enforces uniqueness between its elements and does not allow nulls.
 * A note is considered unique by comparing using {@code Note#isSameNote(Note)}. As such, adding and updating of
 * notes uses Note#isSameNote(Note) for equality so as to ensure that the note being added or updated is
 * unique in terms of identity in the UniqueNoteList. However, the removal of a note uses Note#equals(Object) so
 * as to ensure that the person with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Content#isSameContent(Content)
 */
public class UniqueContentList implements Iterable<Content> {

    private final ObservableList<Content> internalList = FXCollections.observableArrayList();
    private final ObservableList<Content> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent note as the given argument.
     */
    public boolean contains(Content toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameContent);
    }

    /**
     * Adds a note to the list.
     * The note must not already exist in the list.
     */
    public void add(Content toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateContactException();
        }
        internalList.add(toAdd);
    }

    /**
     * Deletes a note to the list.
     */
    public void delete(Content toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate persons.
     */
    public void setContent(List<Content> content) {
        requireAllNonNull(content);
        if (!contentAreUnique(content)) {
            throw new DuplicateContactException();
        }

        internalList.setAll(content);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Content> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Content> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueContentList // instanceof handles nulls
                && internalList.equals(((UniqueContentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code notes} contains only unique notes.
     */
    private boolean contentAreUnique(List<Content> content) {
        for (int i = 0; i < content.size() - 1; i++) {
            for (int j = i + 1; j < content.size(); j++) {
                if (content.get(i).isSameContent(content.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
