package seedu.address.model;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.Event;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.TutorFilter;
import seedu.address.model.grade.Grade;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Tutor;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Tutor> PREDICATE_SHOW_ALL_TUTORS = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENT = unused -> true;
    Predicate<Grade> PREDICATE_SHOW_ALL_GRADE = unused -> true;
    Predicate<Schedule> PREDICATE_SHOW_ALL_SCHEDULE = unused -> true;
    Predicate<Event> PREDICATE_SHOW_ALL_EVENT = unused -> true;
    Predicate<Reminder> PREDICATE_SHOW_ALL_REMINDER = unused -> true;

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
     * Returns the user prefs' tutor book file path.
     */
    Path getTutorBookFilePath();

    /**
     * Sets the user prefs' tutor book file path.
     */
    void setTutorBookFilePath(Path tutorBookFilePath);

    /**
     * Returns the TutorBook
     */
    ReadOnlyTutorBook getTutorBook();

    /**
     * Replaces tutor book data with the data in {@code tutorBook}.
     */
    void setTutorBook(ReadOnlyTutorBook tutorBook);

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
     *
     * @param gradeBookFilePath To be supplied by user
     */
    void setGradeBookFilePath(Path gradeBookFilePath);

    /**
     * Returns true if a tutor with the same identity as {@code tutor} exists in the tutor book.
     */
    boolean hasTutor(Tutor tutor);

    /**
     * @param name Name of tutor to search for.
     * @return True is there exists a tutor with that name.
     */
    boolean hasTutorByName(Name name);

    /**
     * @param subjectName Subject name to query for a particular tutor
     * @return True is tutor teaches subject.
     */
    boolean doesTutorTeachSubject(Name name, SubjectName subjectName);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     * Deletes the given tutor.
     * The tutor must exist in the tutor book.
     */
    void deleteTutor(Tutor target);

    /**
     * Adds the given tutor.
     * {@code tutor} must not already exist in the tutor book.
     */
    void addTutor(Tutor tutor);

    /**
     * Replaces the given tutor {@code target} with {@code editedTutor}.
     * {@code target} must exist in the tutor book.
     * The tutor identity of {@code editedTutor} must not be the same as another existing tutor in the tutor book.
     */
    void setTutor(Tutor target, Tutor editedTutor);

    /**
     * Returns an unmodifiable view of the filtered tutor list
     */
    ObservableList<Tutor> getFilteredTutorList();

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns an unmodifiable view of the filtered grade list
     */
    ObservableList<Grade> getFilteredGradeList();

    /**
     * Updates the filter of the filtered tutor list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTutorList(Predicate<Tutor> predicate);

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
    Appointment removeAppointmentIndex(int indexToRemove);

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
     * Change all names of appointment related to previous tutor to new name.
     */
    void changeAllAppointmentsToName(Name oldName, Name name);

    /**
     * @param name Name of tutor to match.
     * @return True is new appointment to be added clashes.
     */
    boolean doesAppointmentClash(Name name, AppointmentDateTime timeFrom,
                                 AppointmentDateTime timeTo);

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
     *
     * @param budget Budget to update to.
     */
    void editBudget(Budget budget);

    /**
     * Deletes an already existing budget.
     */
    void deleteBudget();

    /**
     * @return Unmodifiable view of the budget list.
     */
    ObservableList<Budget> getBudgetList();

    /**
     * Returns true if a grade with the same identity as {@code grade} exists in the
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
     * Checks if any of the filters are in tutor filter.
     *
     * @param tutorFilter Filters to check for inside model's tutor filter.
     * @return true if model's tutor filter contains a filter that was passed in.
     */
    boolean hasAnyTutorFilter(TutorFilter tutorFilter);

    /**
     * Checks if all of the filters are in tutor filter.
     *
     * @param tutorFilter Filters to check for inside model's tutor filter.
     * @return true if model's tutor filter contains all filters that were passed in.
     */
    boolean hasAllTutorFilters(TutorFilter tutorFilter);

    /**
     * Adds filters to tutor filter.
     *
     * @param tutorFilter Filters to add to model's tutor filter.
     */
    void addTutorFilter(TutorFilter tutorFilter);

    /**
     * Removes filters from tutor filter.
     *
     * @param tutorFilter Filters to remove from model's tutor filter.
     */
    void removeTutorFilter(TutorFilter tutorFilter);

    /**
     * Returns an unmodifiable view of the tutor filter string list.
     */
    ObservableList<String> getTutorFilterStringList();

    /**
     * Checks if any of the filters are in appointment filter.
     *
     * @param appointmentFilter Filters to check for inside model's appointment filter.
     * @return true if model's appointment filter contains a filter that was passed in.
     */
    boolean hasAnyAppointmentFilter(AppointmentFilter appointmentFilter);

    /**
     * Checks if all of the filters are in appointment filter.
     *
     * @param appointmentFilter Filters to check for inside model's appointment filter.
     * @return true if model's appointment filter contains all filters that were passed in.
     */
    boolean hasAllAppointmentFilters(AppointmentFilter appointmentFilter);

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
     * Returns the ScheduleTracker.
     */
    ReadOnlyScheduleTracker getScheduleTracker();

    /**
     * Replaces schedule tracker data with the data in {@code scheduleTracker}.
     */
    void setScheduleTracker(ReadOnlyScheduleTracker scheduleTracker);

    /**
     * Returns an unmodifiable view of the filtered schedule list.
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
     * Checks if Appointment or Schedule have clashing date time in the event list.
     *
     * @param event Event to check
     * @return True if the new Appointment or Schedule have clashes in event list
     */
    boolean hasClashingDateTime(Event event);

    /**
     * Checks if Appointment or Schedule have clashing date time in the event list.
     *
     * @param editedEvent   Event to check
     * @param preEditEvent  Event before edit
     * @return True if the new Appointment or Schedule have clashes in event list
     */
    boolean hasClashingDateTime(Event editedEvent, Event preEditEvent);

    /**
     * Returns the ReminderTracker
     */
    ReadOnlyReminderTracker getReminderTracker();

    /**
     * Replaces reminder tracker data with the data in {@code reminderTracker}.
     */
    void setReminderTracker(ReadOnlyReminderTracker reminderTracker);

    /**
     * Returns an unmodifiable view of the filtered reminder list
     */
    ObservableList<Reminder> getFilteredReminderList();

    /**
     * Updates the filter of the filtered reminder list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredReminderList(Predicate<Reminder> predicate);

    /**
     * Checks if Reminder exists in reminder list.
     *
     * @param reminder Reminder to check
     * @return True if reminder is already in reminder list
     */
    boolean hasReminder(Reminder reminder);

    /**
     * @param reminder Reminder to add (reminder must not already exist)
     */
    void addReminder(Reminder reminder);

    /**
     * Removes reminder from reminder list.
     *
     * @param reminder Reminder to be removed must be present
     */
    void deleteReminder(Reminder reminder);

    /**
     * Replaces the given reminder {@code target} with {@code editedReminder}.
     * {@code target} must exist in the reminder tracker.
     * The {@code editedReminder} must not be the same as another existing reminder in the reminder tracker.
     */
    void setReminder(Reminder target, Reminder editedReminder);

    /**
     * Returns an unmodifiable view of the filtered event list.
     */
    ObservableList<Event> getFilteredEventList();

    /**
     * Sets the query date for Timetable Window.
     */
    void setTimeTableDate(LocalDate date);

    /**
     * Returns the query date for Timetable Window.
     */
    LocalDate getTimeTableDate();

    /**
     * Reset the FilteredList predicates if there's any other operation after clicking on the calendar.
     */
    void resetPredicates();

}
