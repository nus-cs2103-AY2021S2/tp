package seedu.flashback.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.flashback.commons.core.GuiSettings;
import seedu.flashback.logic.commands.Command;
import seedu.flashback.logic.commands.CommandResult;
import seedu.flashback.logic.commands.exceptions.CommandException;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.ReadOnlyFlashBack;
import seedu.flashback.model.flashcard.Flashcard;

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

    /**
     * Executes a given command and returns the result.
     *
     * @param command The command to execute.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    CommandResult execute(Command command) throws CommandException;

    /**
     * Returns Flashback.
     *
     * @see seedu.flashback.model.Model#getFlashBack()
     */
    ReadOnlyFlashBack getFlashBack();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Flashcard> getFilteredFlashcardList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getFlashBackFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
