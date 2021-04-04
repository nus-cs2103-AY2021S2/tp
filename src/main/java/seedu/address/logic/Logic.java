package seedu.address.logic;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.DisplayFilterPredicate;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUniqueAliasMap;
import seedu.address.model.person.Person;

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
     * Returns the AddressBook.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns the Aliases.
     *
     * @see seedu.address.model.Model#getAliases()
     */
    ReadOnlyUniqueAliasMap getAliases();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

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
     * Returns predicate that determines PersonCard control visibility.
     * @return predicate that returns true if prefix linked control should be shown.
     */
    DisplayFilterPredicate getDisplayFilter();

    /**
     * Gets a filtered list of Autocomplete commands given a "starts-with" value.
     *
     * @param value String value to be used as "starts-with" value in filter
     * @return Returns a filtered ObservableList of commands
     */
    ObservableList<String> getAutocompleteCommands(String value);

    /**
     * Returns predicate that determines a Person objects selected state.
     * @return predicate that is true if Person object is selected
     */
    Predicate<Person> getSelectedPersonPredicate();

    /**
     * Returns aliases in an ObservableList of String.
     */
    ObservableList<String> getObservableStringAliases();

    /**
     * Gets a List of flags of a provided command.
     *
     * @param command command to retrieve flags from
     * @return Returns a list of flags for a specified command
     */
    List<String> getAutocompleteFlags(String command);

    /**
     * Returns a list of flags that is not inside the provided current strings.
     *
     * @param command currentStrings
     * @return Returns a list of unused flags for a specified command
     */
    List<String> filterExistingFlags(String currentStrings, String command);

    /**
     * Checks if the given string has flags for autocompletion.
     *
     * @param commandString string provided from a command box
     * @return Returns true if the text provided has flags available for autocompletion
     */
    boolean isAutocompleteFlag(String commandString);

    /**
     * Processes a given command string and returns a list of available commands for a specified command
     * in the commandString.
     *
     * @param commandString string provided from a command box
     * @return Returns a list of available flags for a specified command
     */
    List<String> getAvailableFlags(String commandString);
}
