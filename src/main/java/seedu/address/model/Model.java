package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.PersonFilter;
import seedu.address.model.grade.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENT = unused -> true;
    Predicate<Grade> PREDICATE_SHOW_ALL_GRADE = unused -> true;
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULE = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENT = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the Appointment book
     */
    ReadOnlyAppointmentBook getAppointmentBook();


    void setAppointmentBook(ReadOnlyAppointmentBook readOnlyAppointmentBook);

    /**
     * @return File path of Appointment Book data file
     */
    Path getAppointmentBookFilePath();

    /**
     * Sets appointment book file path.
     *
     * @param appointmentBookFilePath To be supplied by user
     */
    void setAppointmentBookFilePath(Path appointmentBookFilePath);


    ReadOnlyGradeBook getGradeBook();

    void setGradeBook(ReadOnlyGradeBook readOnlyGradeBook);

    /**
     * @return File path of Grade Book data file
     */
    Path getGradeBookFilePath();

    /**
     * Sets grade book file path.
     * @param gradeBookFilePath To be supplied by user
     */
    void setGradeBookFilePath(Path gradeBookFilePath);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * @param name Name of tutor to search for.
     * @return True is there exists a tutor with that name.
     */
    boolean hasTutorByName(Name name);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns an unmodifiable view of the filtered grade list
     */
    ObservableList<Grade> getFilteredGradeList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Updates the filter of the filtered grade list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGradeList(Predicate<Grade> predicate);

    /**
     * Checks if Appointment exists in appointment list.
     *
     * @param appointment Appointment to check
     * @return True if appointment is already in appointment list
     */
    boolean hasAppointment(Appointment appointment);


    /**
     * @param appointment Appointment to add (appointment must not already exist)
     */
    void addAppointment(Appointment appointment);

    /**
     * Removes appointment from appointment list.
     *
     * @param appointment Appointment to remove must be present
     */
    void removeAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The {@code editedAppointment} must not be the same as another existing appointment in the appointment book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Method that removes appointment based on index
     *
     * @param indexToRemove
     */
    void removeAppointmentIndex(int indexToRemove);

    /**
     * Checks if {@code AppointmentDateTime} exists in the appointment list.
     *
     * @param appointmentDateTime Appointment DateTime to be checked
     * @return true if Appointment DateTime exists in the appointment list
     */
    boolean hasAppointmentDateTime(AppointmentDateTime appointmentDateTime);

    /**
     * @param name Name of tutor.
     * @return True if appointment contains tutor.
     */
    boolean hasAppointmentContainingTutor(Name name);

    /**
     * @return Budget Book
     */
    BudgetBook getBudgetBook();

    /**
     * @return Trus if budget already exists.
     */
    boolean hasBudget();

    /**
     * @param budget Budget to verify whether present.
     * @return True if budget already exists.
     */
    boolean hasBudget(Budget budget);

    /**
     * Adds budget is present into budget.txt
     */
    void addBudget(Budget budget);

    /**
     * Edited budget with the given budget.
     * @param budget Budget to update to.
     */
    void editBudget(Budget budget);

    /**
     * Deletes an already existing budget.
     */
    void deleteBudget();


    /** Returns true if a grade with the same identity as {@code grade} exists in the
    * grade book.
    */
    boolean hasGrade(Grade grade);

    /**
     * Deletes the given grade.
     * The grade must exist in the grade book.
     */
    void deleteGrade(Grade grade);

    /**
     * Adds the given grade.
     * {@code grade} must not already exist in the grade book.
     */
    void addGrade(Grade grade);

    /**
     * Replaces the given grade {@code target} with {@code editedGrade}.
     * {@code target} must exist in the grade book.
     * The grade identity of {@code editedGrade} must not be the same as another existing grade in the grade book.
     */
    void setGrade(Grade target, Grade editedGrade);

    /**
     * Method that removes grade based on index
     *
     * @param indexToRemove
     */
    void removeGradeIndex(int indexToRemove);

    /**
     * Checks if any of the filters are in person filter.
     *
     * @param personFilter Filters to check for inside model's person filter.
     * @return true if model's person filter contains a filter that was passed in.
     */
    boolean hasPersonFilter(PersonFilter personFilter);

    /**
     * Adds filters to person filter.
     *
     * @param personFilter Filters to add to model's person filter.
     */
    void addPersonFilter(PersonFilter personFilter);

    /**
     * Removes filters from person filter.
     *
     * @param personFilter Filters to remove from model's person filter.
     */
    void removePersonFilter(PersonFilter personFilter);

    /**
     * Returns an unmodifiable view of the person filter string list.
     */
    ObservableList<String> getPersonFilterStringList();

    /**
     * Checks if any of the filters are in appointment filter.
     *
     * @param appointmentFilter Filters to check for inside model's appointment filter.
     * @return true if model's appointment filter contains a filter that was passed in.
     */
    boolean hasAppointmentFilter(AppointmentFilter appointmentFilter);

    /**
     * Adds filters to appointment filter.
     *
     * @param appointmentFilter Filters to add to model's appointment filter.
     */
    void addAppointmentFilter(AppointmentFilter appointmentFilter);

    /**
     * Removes filters from appointment filter.
     *
     * @param appointmentFilter Filters to remove from model's appointment filter.
     */
    void removeAppointmentFilter(AppointmentFilter appointmentFilter);

    /**
     * Returns an unmodifiable view of the appointment filter string list.
     */
    ObservableList<String> getAppointmentFilterStringList();

    /**
     * Returns the AddressBook
     */
    ReadOnlyScheduleTracker getScheduleTracker();

    /**
     * Replaces address book data with the data in {@code scheduleTracker}.
     */
    void setScheduleTracker(ReadOnlyScheduleTracker scheduleTracker);

    /**
     * Returns an unmodifiable view of the filtered schedule list
     */
    ObservableList<Schedule> getFilteredScheduleList();

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredScheduleList(Predicate<Schedule> predicate);

    /**
     * Checks if Schedule exists in schedule list.
     *
     * @param schedule Schedule to check
     * @return True if schedule is already in schedule list
     */
    boolean hasSchedule(Schedule schedule);

    /**
     * @param schedule Schedule to add (schedule must not already exist)
     */
    void addSchedule(Schedule schedule);

    /**
     * Removes schedule from schedule list.
     *
     * @param schedule Schedule to be removed must be present
     */
    void deleteSchedule(Schedule schedule);

    /**
     * Replaces the given schedule {@code target} with {@code editedSchedule}.
     * {@code target} must exist in the schedule tracker.
     * The {@code editedSchedule} must not be the same as another existing schedule in the schedule tracker.
     */
    void setSchedule(Schedule target, Schedule editedSchedule);

    /**
     * Checks if Appointment or Schedule have clashing date time in event list.
     *
     * @param event Event to check
     * @return True if the new Appointment or Schedule have clashes in event list
     */
    boolean hasClashingDateTime(Event event);

    /**
     * Returns an unmodifiable view of the filtered event list
     */
    ObservableList<Event> getFilteredEventList();
}
