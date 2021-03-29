package seedu.dictionote.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.dictionote.commons.core.GuiSettings;
import seedu.dictionote.logic.commands.CommandResult;
import seedu.dictionote.logic.commands.exceptions.CommandException;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.ReadOnlyContactsList;
import seedu.dictionote.model.contact.Contact;
import seedu.dictionote.model.dictionary.Content;
import seedu.dictionote.model.dictionary.Definition;
import seedu.dictionote.model.dictionary.DisplayableContent;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.ui.DictionaryContentConfig;
import seedu.dictionote.ui.NoteContentConfig;


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
     * @see seedu.dictionote.model.Model#getContactsList()
     */
    ReadOnlyContactsList getContactsList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Contact> getFilteredContactList();

    /** Returns an unmodifiable view of the filtered list of notes */
    ObservableList<Note> getFilteredNoteList();

    /** Returns an unmodifiable view of the filtered list of content */
    ObservableList<Content> getFilteredContentList();

    /** Returns an unmodifiable view of the filtered list of definitions */
    ObservableList<Definition> getFilteredDefinitionList();

    /** Returns an unmodifiable view of the filtered list of current viewable list */
    ObservableList<? extends DisplayableContent> getFilteredCurrentDictionaryList();


    /**
     * Returns the user prefs' contacts list file path.
     */
    Path getContactsListFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Set Dictionary UI Configuration Interface
     */
    void setDictionaryContentConfig(DictionaryContentConfig dictionaryContentConfig);


    /**
     * Set Note UI Configuration Interface
     */
    void setNoteContentConfig(NoteContentConfig noteContentConfig);
}
