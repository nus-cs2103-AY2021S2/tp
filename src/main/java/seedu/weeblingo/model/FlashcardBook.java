package seedu.weeblingo.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.flashcard.UniqueFlashcardList;
import seedu.weeblingo.model.flashcard.UniqueScoreHistoryList;
import seedu.weeblingo.model.score.Score;

/**
 * Wraps all data at the flashcard-book level
 * Duplicates are not allowed (by .isSameFlashcard comparison)
 */
public class FlashcardBook implements ReadOnlyFlashcardBook {

    private final UniqueFlashcardList flashcards;
    private final UniqueScoreHistoryList scores;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        flashcards = new UniqueFlashcardList();
        scores = new UniqueScoreHistoryList();
    }

    public FlashcardBook() {}

    /**
     * Creates an FlashcardBook using the flashcards in the {@code toBeCopied}
     */
    public FlashcardBook(ReadOnlyFlashcardBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the flashcard list with {@code flashcards}.
     * {@code flashcards} must not contain duplicate flashcards.
     */
    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards.setFlashcards(flashcards);
    }

    /**
     * Replaces the contents of the Score history list with {@code scores}.
     * {@code scores} must not contain duplicate attempt Scores.
     */
    public void setScores(List<Score> scores) {
        this.scores.setScores(scores);
    }


    /**
     * Resets the existing data of this {@code FlashcardBook} with {@code newData}.
     */
    public void resetData(ReadOnlyFlashcardBook newData) {
        requireNonNull(newData);
        setScores(newData.getScoreHistoryList());
        setFlashcards(newData.getFlashcardList());
    }


    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the address book.
     */
    public boolean hasFlashcard(Flashcard flashcard) {
        requireNonNull(flashcard);
        return flashcards.contains(flashcard);
    }

    /**
     * Adds a flashcard to the address book.
     * The flashcard must not already exist in the address book.
     */
    public void addFlashcard(Flashcard p) {
        flashcards.add(p);
    }

    /**
     * Replaces the given flashcard {@code target} in the list with {@code editedFlashcard}.
     * {@code target} must exist in the address book.
     * The flashcard identity of {@code editedFlashcard} must not be the same
     * as another existing flashcard in the address book.
     */
    public void setFlashcard(Flashcard target, Flashcard editedFlashcard) {
        requireNonNull(editedFlashcard);

        flashcards.setFlashcard(target, editedFlashcard);
    }

    /**
     * Removes {@code key} from this {@code FlashcardBook}.
     * {@code key} must exist in the address book.
     */
    public void removeFlashcard(Flashcard key) {
        flashcards.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return flashcards.asUnmodifiableObservableList().size() + " ";
    }

    @Override
    public ObservableList<Flashcard> getFlashcardList() {
        return flashcards.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Score> getScoreHistoryList() {
        return scores.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FlashcardBook // instanceof handles nulls
                && flashcards.equals(((FlashcardBook) other).flashcards));
    }

    @Override
    public int hashCode() {
        return flashcards.hashCode();
    }
}
