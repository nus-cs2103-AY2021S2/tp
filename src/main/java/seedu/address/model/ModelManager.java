package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.appointment.DateViewPredicate;
import seedu.address.model.budget.Budget;
import seedu.address.model.event.DateTimeClashPredicate;
import seedu.address.model.event.Event;
import seedu.address.model.filter.AppointmentFilter;
import seedu.address.model.filter.TutorFilter;
import seedu.address.model.grade.Grade;
import seedu.address.model.reminder.ReadOnlyReminderTracker;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderTracker;
import seedu.address.model.schedule.ReadOnlyScheduleTracker;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;
import seedu.address.model.subject.SubjectName;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Tutor;

/**
 * Represents the in-memory model of the tutor book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TutorBook tutorBook;
    private final AppointmentBook appointmentBook;
    private final GradeBook gradeBook;
    private final ScheduleTracker scheduleTracker;
    private final ReminderTracker reminderTracker;
    private final UserPrefs userPrefs;
    private final BudgetBook budgetBook;

    private final TutorFilter tutorFilter;
    private final AppointmentFilter appointmentFilter;
    private final FilteredList<Tutor> filteredTutors;
    private final FilteredList<Appointment> filteredAppointment;
    private final FilteredList<Grade> filteredGrades;
    private final FilteredList<Schedule> filteredSchedules;
    private final FilteredList<Reminder> filteredReminders;
    private final FilteredList<Event> filteredEvents;
    private final ObservableMap<Integer, LocalDate> timeTableDateMap;

    /**
     * Initializes a ModelManager with the given TutorBook, AppointmentBook, BudgetBook, GradeBook, ScheduleTracker,
     * ReminderTracker and userPrefs.
     */
    public ModelManager(ReadOnlyTutorBook tutorBook,
                        ReadOnlyUserPrefs userPrefs,
                        ReadOnlyAppointmentBook appointmentBook,
                        BudgetBook budgetBook, ReadOnlyGradeBook gradeBook,
                        ReadOnlyScheduleTracker scheduleTracker,
                        ReadOnlyReminderTracker reminderTracker) {
        super();
        requireAllNonNull(tutorBook, appointmentBook, userPrefs, budgetBook, scheduleTracker, reminderTracker);
        logger.fine("Initializing Tutor Tracker: with tutor book: " + tutorBook + " and user prefs " + userPrefs);

        this.tutorBook = new TutorBook(tutorBook);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.scheduleTracker = new ScheduleTracker(scheduleTracker);
        this.gradeBook = new GradeBook(gradeBook);
        this.budgetBook = new BudgetBook(budgetBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.reminderTracker = new ReminderTracker(reminderTracker);
        this.tutorFilter = new TutorFilter();
        this.filteredTutors = new FilteredList<>(this.tutorBook.getTutorList());
        this.filteredAppointment = new FilteredList<>(this.appointmentBook.getAppointmentList());
        this.filteredGrades = new FilteredList<>(this.gradeBook.getGradeList());
        this.filteredSchedules = new FilteredList<>(this.scheduleTracker.getScheduleList());
        this.filteredEvents = new FilteredList<>(createEventList(FXCollections.observableArrayList(),
                this.appointmentBook.getAppointmentList(), this.scheduleTracker.getScheduleList()));
        this.appointmentFilter = new AppointmentFilter();
        this.filteredReminders = new FilteredList<>(this.reminderTracker.getReminderList());
        this.timeTableDateMap = FXCollections.observableHashMap();
        timeTableDateMap.put(0, LocalDate.now());
    }

    /**
     * Default constructor without params. Initializes with empty books.
     */
    public ModelManager() {
        this(new TutorBook(), new UserPrefs(), new AppointmentBook(),
                new BudgetBook(), new GradeBook(), new ScheduleTracker(), new ReminderTracker());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTutorBookFilePath() {
        return userPrefs.getTutorBookFilePath();
    }

    @Override
    public void setTutorBookFilePath(Path tutorBookFilePath) {
        requireNonNull(tutorBookFilePath);
        userPrefs.setTutorBookFilePath(tutorBookFilePath);
    }

    @Override
    public Path getAppointmentBookFilePath() {
        return userPrefs.getAppointmentBookFilePath();
    }

    @Override
    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        userPrefs.setAppointmentBookFilePath(appointmentBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public ReadOnlyTutorBook getTutorBook() {
        return tutorBook;
    }

    @Override
    public void setTutorBook(ReadOnlyTutorBook tutorBook) {
        this.tutorBook.resetData(tutorBook);
    }

    @Override
    public boolean hasTutor(Tutor tutor) {
        requireNonNull(tutor);
        return tutorBook.hasTutor(tutor);
    }

    @Override
    public boolean hasTutorByName(Name name) {
        return tutorBook.containsTutorByName(name);
    }

    @Override
    public boolean doesTutorTeachSubject(Name name, SubjectName subjectName) {
        return tutorBook.tutorTeachesSubject(name, subjectName);
    }

    @Override

    public void deleteTutor(Tutor target) {
        tutorBook.removeTutor(target);
    }

    @Override
    public void addTutor(Tutor tutor) {
        tutorBook.addTutor(tutor);
        updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
    }

    @Override
    public void setTutor(Tutor target, Tutor editedTutor) {
        requireAllNonNull(target, editedTutor);

        tutorBook.setTutor(target, editedTutor);
    }

    @Override
    public ObservableList<Budget> getBudgetList() {
        return budgetBook.getBudgetList();
    }

    //=========== AppointmentBook=============================================================================

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return appointmentBook;
    }

    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public boolean hasAppointmentContainingTutor(Name name) {
        return this.appointmentBook.hasAppointmentContainingTutor(name);
    }

    //=========== Filtered Tutor List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Tutor} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Tutor> getFilteredTutorList() {
        return filteredTutors;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAppointmentBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointment;
    }

    /**
     * Updates the filter of the filtered tutor list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredTutorList(Predicate<Tutor> predicate) {
        requireNonNull(predicate);
        filteredTutors.setPredicate(predicate);
    }

    //=========== AppointmentBook============================================================================

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointment.setPredicate(predicate);
    }

    /**
     * Checks if Appointment exists in appointment list.
     *
     * @param appointment Appointment to check
     * @return True if appointment is already in appointment list
     */
    @Override
    public boolean hasAppointment(Appointment appointment) {
        return appointmentBook.hasAppointment(appointment);
    }

    /**
     * @param appointment Appointment to add (appointment must not already exist)
     */
    @Override
    public void addAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
    }

    /**
     * Removes appointment from appointment list.
     *
     * @param appointment Appointment to remove must be present
     */
    @Override
    public void removeAppointment(Appointment appointment) {
        appointmentBook.removeAppointment(appointment);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentBook.setAppointment(target, editedAppointment);
    }

    /**
     * Method that removes appointment based on index
     *
     * @param indexToRemove Index of appointment to remove
     */
    @Override
    public Appointment removeAppointmentIndex(int indexToRemove) {
        return appointmentBook.removeAppointment(indexToRemove);
    }

    /**
     * @param name Name of tutor to match.
     * @return True if new appointment to be added clashes.
     */
    @Override
    public boolean doesAppointmentClash(Name name, AppointmentDateTime timeFrom, AppointmentDateTime timeTo) {
        return appointmentBook.doesAppointmentClash(name, timeFrom, timeTo);
    }


    /**
     * Checks if {@code AppointmentDateTime} exists in the appointment list.
     *
     * @param appointmentDateTime Appointment DateTime to be checked
     * @return true if Appointment DateTime exists in the appointment list
     */
    @Override
    public boolean hasAppointmentDateTime(AppointmentDateTime appointmentDateTime) {
        return !filteredAppointment.filtered(new DateViewPredicate(appointmentDateTime)).isEmpty();
    }


    //============== Budget ============================================================

    /**
     * Getter method to retrieve budget book.
     *
     * @return Budget book.
     */
    public BudgetBook getBudgetBook() {
        return budgetBook;
    }

    /**
     * @return True is budget already exists.
     */
    @Override
    public boolean hasBudget() {
        return budgetBook.hasBudget();
    }

    /**
     * @param budget Budget to verify whether present.
     */
    @Override
    public boolean hasBudget(Budget budget) {
        return budgetBook.hasBudget();
    }

    /**
     * Adds budget to budget book. Budget must not be present.
     *
     * @param budget Budget to add.
     */
    @Override
    public void addBudget(Budget budget) {
        budgetBook.addBudget(budget);
    }

    /**
     * Edits an already present {@code budget}.
     *
     * @param budget Budget to update to.
     */
    @Override
    public void editBudget(Budget budget) {
        budgetBook.setBudget(budget);
    }

    /**
     * Removes an already existing budget.
     */
    @Override
    public void deleteBudget() {
        budgetBook.deleteBudget();
    }

    //=========== GradeList ============================================================================

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredGradeList(Predicate<Grade> predicate) {
        requireNonNull(predicate);
        filteredGrades.setPredicate(predicate);
    }

    public ReadOnlyGradeBook getGradeBook() {
        return gradeBook;

    }

    public void setGradeBook(ReadOnlyGradeBook readOnlyGradeBook) {
        this.gradeBook.resetData(readOnlyGradeBook);
    }

    /**
     * @return File path of Grade Book data file
     */
    public Path getGradeBookFilePath() {
        return userPrefs.getGradeBookFilePath();
    }

    /**
     * Sets grade book file path.
     *
     * @param gradeBookFilePath To be supplied by user
     */
    public void setGradeBookFilePath(Path gradeBookFilePath) {
        requireNonNull(gradeBookFilePath);
        userPrefs.setGradeBookFilePath(gradeBookFilePath);
    }

    /**
     * Returns true if a grade with the same identity as {@code grade} exists in the grade book.
     */
    public boolean hasGrade(Grade grade) {
        requireNonNull(grade);
        return gradeBook.hasGrade(grade);
    }

    /**
     * Deletes the given grade.
     * The grade must exist in the grade book.
     */
    public void deleteGrade(Grade target) {
        gradeBook.removeGrade(target);
    }

    /**
     * Adds the given grade.
     * {@code grade} must not already exist in the grade book.
     */
    public void addGrade(Grade grade) {
        gradeBook.addGrade(grade);
    }

    /**
     * Replaces the given grade {@code target} with {@code editedGrade}.
     * {@code target} must exist in the grade book.
     * The grade identity of {@code editedGrade} must not be the same as another existing grade in the grade book.
     */
    public void setGrade(Grade target, Grade editedGrade) {
        requireAllNonNull(target, editedGrade);
        gradeBook.setGrade(target, editedGrade);
    }

    /**
     * Method that removes grade based on index
     *
     * @param indexToRemove index of grade to remove
     */
    @Override
    public void removeGradeIndex(int indexToRemove) {
        gradeBook.removeGrade(indexToRemove);
    }

    /**
     * Returns an unmodifiable view of the filtered grade list
     */
    public ObservableList<Grade> getFilteredGradeList() {
        return filteredGrades;
    }

    //=========== TutorFilter =====================================================================
    @Override
    public boolean hasAnyTutorFilter(TutorFilter tutorFilter) {
        return this.tutorFilter.hasAny(tutorFilter);
    }

    @Override
    public boolean hasAllTutorFilters(TutorFilter tutorFilter) {
        return this.tutorFilter.hasAll(tutorFilter);
    }

    @Override
    public void addTutorFilter(TutorFilter tutorFilter) {
        this.tutorFilter.add(tutorFilter);

        // Required workaround for bug where filtered list would not trigger update
        this.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        this.updateFilteredTutorList(this.tutorFilter);
    }

    @Override
    public void removeTutorFilter(TutorFilter tutorFilter) {
        this.tutorFilter.remove(tutorFilter);

        // Required workaround for bug where filtered list would not trigger update
        this.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);

        this.updateFilteredTutorList(this.tutorFilter);
    }

    @Override
    public ObservableList<String> getTutorFilterStringList() {
        return this.tutorFilter.asUnmodifiableObservableList();
    }

    //=========== AppointmentFilter =====================================================================
    @Override
    public boolean hasAnyAppointmentFilter(AppointmentFilter appointmentFilter) {
        return this.appointmentFilter.hasAny(appointmentFilter);
    }

    @Override
    public boolean hasAllAppointmentFilters(AppointmentFilter appointmentFilter) {
        return this.appointmentFilter.hasAll(appointmentFilter);
    }

    @Override
    public void addAppointmentFilter(AppointmentFilter appointmentFilter) {
        this.appointmentFilter.add(appointmentFilter);

        // Required workaround for bug where filtered list would not trigger update
        this.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT);

        this.updateFilteredAppointmentList(this.appointmentFilter);
    }

    @Override
    public void removeAppointmentFilter(AppointmentFilter appointmentFilter) {
        this.appointmentFilter.remove(appointmentFilter);

        // Required workaround for bug where filtered list would not trigger update
        this.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT);

        this.updateFilteredAppointmentList(this.appointmentFilter);
    }

    @Override
    public ObservableList<String> getAppointmentFilterStringList() {
        return this.appointmentFilter.asUnmodifiableObservableList();
    }

    /* Schedule Methods */
    /**
     * Returns the ScheduleTracker.
     */
    @Override
    public ReadOnlyScheduleTracker getScheduleTracker() {
        return scheduleTracker;
    }

    /**
     * Replaces schedule tracker data with the data in {@code scheduleTracker}.
     */
    @Override
    public void setScheduleTracker(ReadOnlyScheduleTracker scheduleTracker) {
        this.scheduleTracker.resetData(scheduleTracker);
    }

    /**
     * Returns an unmodifiable view of the filtered schedule list.
     */
    @Override
    public ObservableList<Schedule> getFilteredScheduleList() {
        return filteredSchedules;
    }

    /**
     * Updates the filter of the filtered schedule list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
        requireNonNull(predicate);
        filteredSchedules.setPredicate(predicate);
    }

    /**
     * Checks if Schedule exists in schedule list.
     *
     * @param schedule Schedule to check
     * @return True if schedule is already in schedule list
     */
    @Override
    public boolean hasSchedule(Schedule schedule) {
        return scheduleTracker.hasSchedule(schedule);
    }

    /**
     * @param schedule Schedule to add (schedule must not already exist)
     */
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleTracker.addSchedule(schedule);
    }

    /**
     * Removes schedule from schedule list.
     *
     * @param schedule Schedule to be removed must be present
     */
    @Override
    public void deleteSchedule(Schedule schedule) {
        scheduleTracker.removeSchedule(schedule);
    }

    /**
     * Replaces the given schedule {@code target} with {@code editedSchedule}.
     * {@code target} must exist in the schedule tracker.
     * The {@code editedSchedule} must not be the same as another existing schedule in the schedule tracker.
     */
    @Override
    public void setSchedule(Schedule target, Schedule editedSchedule) {
        scheduleTracker.setSchedule(target, editedSchedule);
    }

    /**
     * Checks if Appointment or Schedule have clashing date time in the event list.
     *
     * @param event Event to check
     * @return True if the new Appointment or Schedule have clashes in event list
     */
    @Override
    public boolean hasClashingDateTime(Event event) {
        requireNonNull(event);
        return filteredEvents.stream().anyMatch(new DateTimeClashPredicate(event));
    }

    /**
     * Checks if Appointment or Schedule have clashing date time in the event list.
     *
     * @param editedEvent   Event to check
     * @param preEditEvent  Event before edit
     * @return True if the new Appointment or Schedule have clashes in event list
     */
    @Override
    public boolean hasClashingDateTime(Event editedEvent, Event preEditEvent) {
        requireNonNull(editedEvent);
        requireNonNull(preEditEvent);
        return filteredEvents.stream().anyMatch(new DateTimeClashPredicate(editedEvent, preEditEvent));
    }

    /**
     * Returns the ReminderTracker
     */
    @Override
    public ReadOnlyReminderTracker getReminderTracker() {
        return reminderTracker;
    }

    /**
     * Replaces reminder tracker data with the data in {@code reminderTracker}.
     */
    @Override
    public void setReminderTracker(ReadOnlyReminderTracker reminderTracker) {
        this.reminderTracker.resetData(reminderTracker);
    }

    /**
     * Returns an unmodifiable view of the filtered reminder list
     */
    @Override
    public ObservableList<Reminder> getFilteredReminderList() {
        return filteredReminders;
    }

    /**
     * Updates the filter of the filtered reminder list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    @Override
    public void updateFilteredReminderList(Predicate<Reminder> predicate) {
        requireNonNull(predicate);
        filteredReminders.setPredicate(predicate);
    }

    /**
     * Checks if Reminder exists in reminder list.
     *
     * @param reminder Reminder to check
     * @return True if reminder is already in reminder list
     */
    @Override
    public boolean hasReminder(Reminder reminder) {
        return reminderTracker.hasReminder(reminder);
    }

    /**
     * @param reminder Reminder to add (reminder must not already exist)
     */
    @Override
    public void addReminder(Reminder reminder) {
        reminderTracker.addReminder(reminder);
    }

    /**
     * Removes reminder from reminder list.
     *
     * @param reminder Reminder to be removed must be present
     */
    @Override
    public void deleteReminder(Reminder reminder) {
        reminderTracker.removeReminder(reminder);
    }

    /**
     * Replaces the given reminder {@code target} with {@code editedReminder}.
     * {@code target} must exist in the reminder tracker.
     * The {@code editedReminder} must not be the same as another existing reminder in the reminder tracker.
     */
    @Override
    public void setReminder(Reminder target, Reminder editedReminder) {
        reminderTracker.setReminder(target, editedReminder);
    }

    /**
     * Returns an unmodifiable view of the filtered event list.
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    /**
     * Sets the query date for Timetable Window.
     */
    @Override
    public void setTimeTableDate(LocalDate date) {
        timeTableDateMap.put(0, date);
    }

    /**
     * Returns the query date for Timetable Window.
     */
    @Override
    public LocalDate getTimeTableDate() {
        return timeTableDateMap.get(0);
    }

    @Override
    public void resetPredicates() {
        filteredAppointment.setPredicate(PREDICATE_SHOW_ALL_APPOINTMENT);
        filteredSchedules.setPredicate(PREDICATE_SHOW_ALL_SCHEDULE);
    }

    //@@author Jens-Peter Haack-reused
    //Reused from
    //https://stackoverflow.com/questions/27644878/binding-an-observablelist-to-contents-of-two-other-observablelists
    //with minor modifications
    /**
     * Creates an EventList which keeps track of both Appointments and Schedules.
     * Primarily used to check if there are any overlapping or clashing dates between Events.
     * @param eventList Event ObservableList to be maintained
     * @param lists     Lists to be populated to the Event ObservableList.
     */
    @SafeVarargs
    private ObservableList<Event> createEventList(ObservableList<Event> eventList,
                                                  ObservableList<? extends Event>... lists) {
        for (ObservableList<? extends Event> list : lists) {
            eventList.addAll(list);
            list.addListener((ListChangeListener.Change<? extends Event> event) -> {
                while (event.next()) {
                    if (event.wasAdded()) {
                        eventList.addAll(event.getAddedSubList());
                    }
                    if (event.wasRemoved()) {
                        eventList.removeAll(event.getRemoved());
                    }
                }
            });
        }
        return eventList;
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return tutorBook.equals(other.tutorBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTutors.equals(other.filteredTutors)
                && tutorFilter.equals(other.tutorFilter)
                && filteredAppointment.equals(other.filteredAppointment)
                && appointmentBook.equals(other.appointmentBook)
                && gradeBook.equals(other.gradeBook)
                && filteredGrades.equals(other.filteredGrades)
                && budgetBook.equals(other.budgetBook)
                && filteredSchedules.equals(other.filteredSchedules)
                && scheduleTracker.equals(other.scheduleTracker)
                && filteredReminders.equals(other.filteredReminders)
                && reminderTracker.equals(other.reminderTracker);
    }
}
