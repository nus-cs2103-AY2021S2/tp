package seedu.iScam.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.iScam.commons.core.GuiSettings;
import seedu.iScam.logic.commands.CommandResult;
import seedu.iScam.logic.commands.exceptions.CommandException;
import seedu.iScam.logic.parser.exceptions.ParseException;
import seedu.iScam.model.ReadOnlyClientBook;
import seedu.iScam.model.client.Client;

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
     * Returns the ClientBook.
     *
     * @see seedu.iScam.model.Model#getAddressBook()
     */
    ReadOnlyClientBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of clients */
    ObservableList<Client> getFilteredClientList();

    /**
     * Returns the user prefs' iScam book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
