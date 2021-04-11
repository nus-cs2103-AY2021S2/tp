package seedu.weeblingo.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.weeblingo.commons.core.GuiSettings;
import seedu.weeblingo.logic.commands.CommandResult;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.logic.parser.exceptions.ParseException;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ReadOnlyFlashcardBook;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.score.Score;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /** Gets the ReadOnlyFlashcardBook object generated that is associated with the current Logic object. */
    ReadOnlyFlashcardBook getFlashcardBook();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /** Returns an unmodifiable view of the filtered list of scores */
    ObservableList<Score> getFilteredScoreHistoryList();


    /** Returns the index of current question in the quiz session. If not in quiz session, returns LIST_INDEX.*/
    int getCurrentIndex();

    /**
     * Returns the user prefs' flashcard book file path.
     */
    Path getFlashcardBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the Model object associated with the Logic object.
     */
    Model getModel();

    /**
     * Returns current mode Weeblingo is in
     */
    int getCurrentMode();

    /**
     * Returns true if flashcards should be shown.
     */
    boolean isPanelVisible();

    /**
     * Returns true if answer to flashcards should be shown.
     */
    boolean isAnswerVisible();

    /**
     * Returns true if the user is viewing history mode.
     */
    boolean isShowingHistory();
}
