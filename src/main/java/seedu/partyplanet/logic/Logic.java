package seedu.partyplanet.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.partyplanet.commons.core.GuiSettings;
import seedu.partyplanet.logic.commands.CommandResult;
import seedu.partyplanet.logic.commands.exceptions.CommandException;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.ReadOnlyAddressBook;
import seedu.partyplanet.model.ReadOnlyEventBook;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.person.Person;

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
     * Parses the command and returns the autocompleted command.
     * @param commandText The command as entered by the user.
     * @return The result of the autocompletion.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseExceptionIf an error occurs during parsing.
     */
    String autoComplete(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.partyplanet.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the EventBook.
     *
     * @see seedu.partyplanet.model.Model#getEventBook()
     */
    ReadOnlyEventBook getEventBook();

    /** Returns an unmodifiable view of the filtered list of events */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' event book file path.
     */
    Path getEventBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
