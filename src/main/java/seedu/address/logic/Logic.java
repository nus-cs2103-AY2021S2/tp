package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyFlashBack;
import seedu.address.model.flashcard.Flashcard;

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
     * @see seedu.address.model.Model#getFlashBack()
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
