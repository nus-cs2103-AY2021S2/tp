package seedu.ta.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.ta.commons.core.GuiSettings;
import seedu.ta.logic.commands.CommandResult;
import seedu.ta.logic.commands.exceptions.CommandException;
import seedu.ta.logic.parser.exceptions.ParseException;
import seedu.ta.model.ReadOnlyTeachingAssistant;
import seedu.ta.model.contact.Contact;
import seedu.ta.model.entry.Entry;

/**
 * API of the Logic component.
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
     * Returns the TeachingAssistant.
     *
     * @see seedu.ta.model.Model#getTeachingAssistant()
     */
    ReadOnlyTeachingAssistant getTeachingAssistant();

    /** Returns an unmodifiable view of the filtered list of contacts. */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered list of entries. */
    ObservableList<Entry> getFilteredEntryList();

    /**
     * Returns the user prefs' Teaching Assistant file path.
     */
    Path getTeachingAssistantFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
