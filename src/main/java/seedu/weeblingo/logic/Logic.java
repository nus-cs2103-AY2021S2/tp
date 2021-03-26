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

    ReadOnlyFlashcardBook getFlashcardBook();

    /** Returns an unmodifiable view of the filtered list of flashcards */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /** Returns an unmodifiable view of the filtered list of scores */
    ObservableList<Score> getFilteredScoreHistoryList();




    /** Returns the current question number in the Quiz */
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
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     *
     */
    Model getModel();
}
