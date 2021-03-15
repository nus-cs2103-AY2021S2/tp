package seedu.dictionote.model.dictionary;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.exceptions.DuplicateDefinitionException;

/**
 * A list of definitions that enforces uniqueness between its elements and does not allow nulls.
 * A definition is considered unique by comparing using {@code DefinitionBook#isSameDefinition(DefinitionBook)}.
 * As such, adding and updating of definitions uses Definitions#isSameDefinitions(Definitions) for equality so
 * as to ensure that the definition being added or updated is unique in terms of identity in the UniqueDefinitionList.
 * However, the removal of a DefinitionBook uses DefinitionBook#equals(Object) so as to ensure that
 * the definition with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Definition#isSameDefinition(Definition)
 */
public class UniqueDefinitionList implements Iterable<Definition> {

    private final ObservableList<Definition> internalList = FXCollections.observableArrayList();
    private final ObservableList<Definition> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent note as the given argument.
     */
    public boolean contains(Definition toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameDefinition);
    }

    /**
     * Adds content to the list.
     * The content must not already exist in the list.
     */
    public void add(Definition toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateDefinitionException();
        }
        internalList.add(toAdd);
    }

    /**
     * Deletes a note to the list.
     */
    public void delete(Definition toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    /**
     * Replaces the contents of this list with {@code notes}.
     * {@code notes} must not contain duplicate persons.
     */
    public void setDefinition(List<Definition> definition) {
        requireAllNonNull(definition);
        if (!definitionAreUnique(definition)) {
            throw new DuplicateDefinitionException();
        }

        internalList.setAll(definition);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Definition> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Definition> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueDefinitionList // instanceof handles nulls
                && internalList.equals(((UniqueDefinitionList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code notes} contains only unique notes.
     */
    private boolean definitionAreUnique(List<Definition> definitions) {
        for (int i = 0; i < definitions.size() - 1; i++) {
            for (int j = i + 1; j < definitions.size(); j++) {
                if (definitions.get(i).isSameDefinition(definitions.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
