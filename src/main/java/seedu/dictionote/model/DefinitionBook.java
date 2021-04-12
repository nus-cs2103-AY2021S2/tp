package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.UniqueDefinitionList;

/**
 * Wraps all data at the definitionbook level.
 * Duplicates are not allowed (by .isSameDefinition comparison).
 */
public class DefinitionBook implements ReadOnlyDefinitionBook {
    private final UniqueDefinitionList definitions;

    {
        definitions = new UniqueDefinitionList();
    }

    public DefinitionBook() {}

    /**
     * Creates a DefinitionBook using the Definitions in the {@code toBeCopied}.
     */

    public DefinitionBook(ReadOnlyDefinitionBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the content list with {@code definition}.
     * {@code definition} must not contain duplicate definition.
     */
    public void setDefinition(List<Definition> definitions) {
        this.definitions.setDefinition(definitions);
    }

    /**
     * Resets the existing data of this {@code Dictionary} with {@code newData}.
     */
    public void resetData(ReadOnlyDefinitionBook newData) {
        requireNonNull(newData);

        setDefinition(newData.getDefinitionList());
    }

    //// note-level operations

    /**
     * Returns true if a content with the same content as {@code definition} exists in the dictionote book.
     */
    public boolean hasDefinition(Definition definition) {
        requireNonNull(definition);
        return definitions.contains(definition);
    }

    /**
     * Adds a definition to the dictionote book.
     * The definition must not already exist in the dictionote book.
     */
    public void addDefinition(Definition n) {
        definitions.add(n);
    }


    //// util methods

    @Override
    public String toString() {
        return definitions.asUnmodifiableObservableList().size() + " definition";
    }

    @Override
    public ObservableList<Definition> getDefinitionList() {
        return definitions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DefinitionBook // instanceof handles nulls
                && definitions.equals(((DefinitionBook) other).definitions));
    }

    @Override
    public int hashCode() {
        return definitions.hashCode();
    }

}
