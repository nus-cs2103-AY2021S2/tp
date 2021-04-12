package seedu.dictionote.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.UniqueContentList;

/**
 * Wraps all data at the notebook level.
 * Duplicates are not allowed (by .isSamePerson comparison).
 */
public class Dictionary implements ReadOnlyDictionary {
    private final UniqueContentList contents;

    {
        contents = new UniqueContentList();
    }

    public Dictionary() {}

    /**
     * Makes a dictionarybook.  //Todo change
     *
     * @param toBeCopied ReadOnlyDictionary
     */

    public Dictionary(ReadOnlyDictionary toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the content list with {@code notes}.
     * {@code content} must not contain duplicate content.
     */
    public void setContents(List<Content> contents) {
        this.contents.setContent(contents);
    }

    /**
     * Resets the existing data of this {@code Dictionary} with {@code newData}.
     */
    public void resetData(ReadOnlyDictionary newData) {
        requireNonNull(newData);

        setContents(newData.getContentList());
    }

    //// note-level operations

    /**
     * Returns true if a content with the same content as {@code note} exists in the dictionote book.
     */
    public boolean hasContent(Content content) {
        requireNonNull(content);
        return contents.contains(content);
    }

    /**
     * Adds a content to the dictionote book.
     * The content must not already exist in the dictionote book.
     */
    public void addContent(Content n) {
        contents.add(n);
    }

    public void deleteContent(Content n) {
        contents.delete(n);
    }


    //// util methods

    @Override
    public String toString() {
        return contents.asUnmodifiableObservableList().size() + " content";
        // TODO: refine later
    }

    @Override
    public ObservableList<Content> getContentList() {
        return contents.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Dictionary // instanceof handles nulls
                && contents.equals(((Dictionary) other).contents));
    }

    @Override
    public int hashCode() {
        return contents.hashCode();
    }

}
