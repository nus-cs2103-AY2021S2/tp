package seedu.weeblingo.model;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.flashcard.Answer;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;
import seedu.weeblingo.model.tag.Tag;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Flashcard> PREDICATE_SHOW_ALL_FLASHCARDS = unused -> true;
    /** {@code Predicate} that always evaluate to true */
    Predicate<Score> PREDICATE_SHOW_ALL_SCORES = unused -> true;

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
     * Returns the user prefs' flashcard book file path.
     */
    Path getFlashcardBookFilePath();

    /**
     * Sets the user prefs' flashcard book file path.
     */
    void setFlashcardBookFilePath(Path flashcardBookFilePath);

    /**
     * Replaces flashcard book data with the data in {@code flashcardBook}.
     */
    void setFlashcardBook(ReadOnlyFlashcardBook flashcardBook);

    /** Returns the FlashcardBook */
    ReadOnlyFlashcardBook getFlashcardBook();

    /**
     * Returns true if a flashcard with the same identity as {@code flashcard} exists in the flashcard book.
     */
    boolean hasFlashcard(Flashcard flashcard);

    /**
     * Deletes the given flashcard.
     * The flashcard must exist in the flashcard book.
     */
    void deleteFlashcard(Flashcard target);

    /**
     * Adds the given flashcard.
     * {@code flashcard} must not already exist in the flashcard book.
     */
    void addFlashcard(Flashcard flashcard);

    /**
     * Replaces the given flashcard {@code target} with {@code editedFlashcard}.
     * {@code target} must exist in the flashcard book.
     * The flashcard identity of {@code editedFlashcard} must not be the same as
     * another existing flashcard in the flashcard book.
     */
    void setFlashcard(Flashcard target, Flashcard editedFlashcard);

    /** Returns an unmodifiable view of the filtered flashcard list */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /** Returns an unmodifiable view of the filtered score history list */
    ObservableList<Score> getFilteredScoreHistory();

    /**
     * Updates the filter of the filtered flashcard list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFlashcardList(Predicate<Flashcard> predicate);

    /**
     * Updates the filter of the filtered score history list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScoreHistory(Predicate<Score> predicate);

    /** Generates a Quiz object and shows the first question
     * @param numberOfQuestions The specified length of the quiz.
     * @param tags The specified tags by which to filter the questions.
     * */
    void startQuiz(int numberOfQuestions, Set<Tag> tags) throws CommandException;

    /**
     * Gets the quiz object in the model
     */
    Quiz getQuizInstance() throws CommandException;

    /** Shows the next question in the Quiz */
    Flashcard getNextFlashcard();

    /** Returns the index of current question for a started a quiz session.*/
    int getCurrentIndex();

    /** Shows all attempted questions in the Quiz */
    void showAttemptedQuestions();

    /** Clears the Quiz instance when the "end" command is called */
    void clearQuizInstance();

    /**
     * Checks if the given attempt matches answer of currently tested flashcard in quiz session
     */
    boolean isCorrectAnswer(Answer attempt);

    /** Returns the Mode object */
    Mode getMode();

    /** Returns the current mode of the app */
    int getCurrentMode();

    /** Sets a tag */
    void tagFlashcard(Flashcard target, String tag);

    void switchModeQuiz() throws CommandException;

    void switchModeLearn() throws CommandException;

    /** Switches the GUI display mode to be 'menu' */
    void switchModeMenu();

    /** Switches the GUI display mode to be 'quiz' */
    void switchModeQuizSession();

    /** Switches the GUI display mode to be 'answer-check-success' */
    void switchModeCheckSuccess();

    /** Switches the GUI display mode to be 'history' */
    void switchModeHistory();

    /** Switches the GUI display mode to be 'end-of-quiz-session' */
    void switchModeQuizSessionEnded();

    /** Gets the statistics information as a string for display purposes */
    String getQuizStatisticString();

    /** Gets the indexes of questions answered correctly in existing quiz session*/
    List<Integer> getCorrectAttemptsIndexes();

    /** Add a score to the FlashcardBook */
    void addScore();
}
