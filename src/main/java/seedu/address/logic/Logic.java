package seedu.address.logic;

import java.nio.file.Path;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyTutorBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.grade.Grade;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tutor.Tutor;

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
     * Returns the TutorBook.
     *
     * @see seedu.address.model.Model#getTutorBook()
     */
    ReadOnlyTutorBook getTutorBook();

    /**
     * Returns an unmodifiable view of the filtered list of persons.
     */
    ObservableList<Tutor> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered list of appointments.
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns an unmodifiable view of the filtered list of events.
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Returns an unmodifiable view of the filtered list of grades.
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
     * Returns an unmodifiable view of the filtered list of schedules.
     */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Returns an unmodifiable view of the appointment filter string list.
     */
    ObservableList<Reminder> getFilteredReminderList();

    /**
     * @return An unmodifiable view of the budget list.
     */
    ObservableList<Budget> getBudgetList();

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
     * Returns the query date for Timetable Window.
     */
    LocalDate getTimeTableDate();
}
