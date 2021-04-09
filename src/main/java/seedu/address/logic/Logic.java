package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commandhistory.CommandHistorySelector;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.commandhistory.ReadOnlyCommandHistory;
import seedu.address.model.issue.Issue;
import seedu.address.model.resident.Resident;
import seedu.address.model.room.Room;

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
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of residents */
    ObservableList<Resident> getFilteredResidentList();

    /** Returns an unmodifiable view of the filtered list of rooms */
    ObservableList<Room> getFilteredRoomList();

    /** Returns an unmodifiable view of the filtered list of issues */
    ObservableList<Issue> getFilteredIssueList();

    /**
     * Returns the user prefs' address book file path.
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

    /**
     * Returns the command history.
     */
    ReadOnlyCommandHistory getCommandHistory();

    /**
     * Returns the command history selector.
     */
    CommandHistorySelector getCommandHistorySelector();

    /**
     * Returns true if the previous undo can be redone. False otherwise.
     *
     * @return True if the previous undo can be redone. False otherwise.
     */
    boolean canRedoAddressBook();

    /**
     * Returns true if the previous command can be undone. False otherwise.
     *
     * @return True if the previous command can be undone. False otherwise.
     */
    boolean canUndoAddressBook();
}
