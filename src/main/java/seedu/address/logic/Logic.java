package seedu.address.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.event.Event;
import seedu.address.model.grade.Grade;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

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

    /**
     * Returns an unmodifiable view of the filtered list of persons
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of appointments
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns an unmodifiable view of the filtered list of events
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns an unmodifiable view of the filtered list of grades
     */
    ObservableList<Grade> getFilteredGradeList();

    /**
     * Returns an unmodifiable view of the person filter string list.
     */
    ObservableList<String> getPersonFilterStringList();

    /**
     * Returns an unmodifiable view of the appointment filter string list.
     */
    ObservableList<String> getAppointmentFilterStringList();

    /**
     * Returns an unmodifiable view of the filtered list of schedules
     */
    ObservableList<Schedule> getFilteredScheduleList();

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
