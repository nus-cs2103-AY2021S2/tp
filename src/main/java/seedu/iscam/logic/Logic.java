package seedu.iscam.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.iscam.commons.core.GuiSettings;
import seedu.iscam.logic.commands.CommandResult;
import seedu.iscam.logic.commands.exceptions.CommandException;
import seedu.iscam.logic.parser.exceptions.ParseException;
import seedu.iscam.model.ObservableClient;
import seedu.iscam.model.ReadOnlyClientBook;
import seedu.iscam.model.client.Client;
import seedu.iscam.model.meeting.Meeting;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the ClientBook.
     *
     * @see seedu.iscam.model.Model#getClientBook()
     */
    ReadOnlyClientBook getAddressBook();

    /**
     * Returns an unmodifiable view of the filtered list of clients
     */
    ObservableList<Client> getFilteredClientList();
    
    /**
     * Returns an unmodifiable view of the filtered list of meetings
     */
    ObservableList<Meeting> getFilteredMeetingList();

    /**
     * Returns an unmodifiable view of a client to be displayed in detail
     */
    ObservableClient getDetailedClient();

    /**
     * Returns the user prefs' iscam book file path.
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
