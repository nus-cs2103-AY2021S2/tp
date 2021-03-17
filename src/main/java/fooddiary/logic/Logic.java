package fooddiary.logic;

import java.nio.file.Path;

import fooddiary.model.Model;
import fooddiary.model.ReadOnlyAddressBook;
import javafx.collections.ObservableList;
import fooddiary.commons.core.GuiSettings;
import fooddiary.logic.commands.CommandResult;
import fooddiary.logic.commands.exceptions.CommandException;
import fooddiary.logic.parser.exceptions.ParseException;
import fooddiary.model.entry.Entry;

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
     * @see Model#getFoodDiary()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Entry> getFilteredPersonList();

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
}
