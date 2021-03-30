package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.flashcard.Flashcard;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' FlashBack file path.
     */
    Path getFlashBackFilePath();

    /**
     * Sets the user prefs' FlashBack file path.
     */
    void setFlashBackFilePath(Path flashBackFilePath);

    /**
     * Replaces FlashBack data with the data in {@code flashBack}.
     */
    void setFlashBack(ReadOnlyFlashBack flashBack);

    /** Returns the FlashBack */
    ReadOnlyFlashBack getFlashBack();

    /**
     * Returns true if a flash card with the same identity as {@code flashcard} exists in FlashBack.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flash card.
     * The flash card must exist in FlashBack
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flash card.
     * {@code flashcard} must not already exist in FlashBack
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flash card {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in FlashBack.
     * The flash card identity of {@code editedFlashCard} must not be the same as another existing card in FlashBack.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered card list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Updates the filter of the filtered card list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Returns true if the model can be undo
     */
    boolean canUndoFlashBack();

    /**
     * Saves the current FlashBack state for undo.
     */
    void commitFlashBack();

    /**
     * Restores FlashBack to its previous state.
     */
    void undoFlashBack();

    /**
     * Sorts the filtered flashcard list according to the supplied {@code comparator}
     * @param comparator the supplied comparator
     */
    void sortFilteredFlashcardList(Comparator<Flashcard> comparator);

    /**
     * Returns true if model can be redo
     */
    boolean canRedoFlashBack();

    /**
     * Restores FlashBack to its state before the undo command
     */
    void redoFlashBack();
}
