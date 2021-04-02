package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDate;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.ReadOnlyMeetingBook;
import seedu.address.model.note.Note;
import seedu.address.model.note.ReadOnlyNoteBook;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyAddressBook;

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
     * @see Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the MeetingBook.
     *
     * @see Model#getMeetingBook()
     */
    ReadOnlyMeetingBook getMeetingBook();

    /** Returns an unmodifiable view of the filtered list of meetings */
    ObservableList<Meeting> getFilteredMeetingList();

    ReadOnlyNoteBook getNoteBook();

    ObservableList<Note> getFilteredNoteList();

    Path getNoteBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the internal unmodifiable Observable list of all meetings inside meeting book
     */
    ObservableList<Meeting> getAllMeetingList();

    /**
     * Gets the observable value of the timetable start date.
     * @return
     */
    public ObservableValue<LocalDate> getTimeTableStartDate();
}
