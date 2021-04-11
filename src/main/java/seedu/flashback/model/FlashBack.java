package seedu.flashback.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.flashcard.UniqueFlashcardList;

/**
 * Wraps all data at the FlashBack level
 * Duplicates are not allowed (by .isSameCard comparison)
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
     * Creates an FlashBack using the cards in the {@code toBeCopied}
     */
    public FlashBack(ReadOnlyFlashBack toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the card list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flash cards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setCards(flashcards);
    }

    /**
     * Resets the existing data of this {@code FlashCard} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashBack newData) {
        requireNonNull(newData);
        setFlashcards(newData.getCardList());
    }

    //// card-level operations

    /**
     * Returns true if a card with the same identity as {@code flashcard} exists in FlashBack
     */
    public boolean hasCard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a card to FlashBack.
     * The card must not already exist in FlashBack.
     */
    public void addCard(Flashcard p) {
        flashcards.add(p);
    }

    /**
     * Replaces the given card {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in FlashBack.
     * The card identity of {@code editedFlashcard} must not be the same as another existing card in FlashBack.
     */
    public void setCard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setCard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashBack}.
     * {@code key} must exist in FlashBack.
     */
    public void removeCard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " cards";
        // TODO: refine later
    }

    @Override
    public ObservableList<Flashcard> getCardList() {
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
