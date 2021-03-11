package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the FlashBack level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashBack implements ReadOnlyFlashBack {

    private final UniqueFlashcardList flashcards;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
    }

    public FlashBack() {}

    /**
     * Creates a FlashBack using the Flashcards in the {@code toBeCopied}
     */
    public FlashBack(ReadOnlyFlashBack toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Resets the existing data of this {@code FlashBack} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashBack newData) {
        requireNonNull(newData);

        setFlashcards(newData.getFlashcardList());
    }

    //// person-level operations

    /**
     * Returns true if a flashcard with the same information as {@code flashcard} exists in the flashcard list.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to flashcard list.
     * The flashcard must not already exist in the flashcard list.
     */
    public void addFlashcard(Flashcard f) {
        flashcards.add(f);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the flashcard list.
     * The flashcard information of {@code editedFlashcard} must not be the same as another existing flashcard in
     * the flashcard list.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from {@code FlashBack}.
     * {@code key} must exist in the flashcard list.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashBack // instanceof handles nulls
                && flashcards.equals(((FlashBack) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
