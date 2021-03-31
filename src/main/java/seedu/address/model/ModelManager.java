package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Assignment;
import seedu.address.model.module.Description;
import seedu.address.model.module.Exam;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the RemindMe data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final RemindMe remindMe;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> filteredModules;
    private final FilteredList<GeneralEvent> filteredEvents;

    /**
     * Initializes a ModelManager with the given RemindMe and userPrefs.
     */
    public ModelManager(ReadOnlyRemindMe remindMeApp,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(remindMeApp, userPrefs);

        logger.fine("Initializing with RemindMe: " + remindMeApp + " and user prefs " + userPrefs);

        this.remindMe = new RemindMe(remindMeApp);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.remindMe.getPersonList());
        filteredModules = new FilteredList<>(this.remindMe.getModuleList());
        filteredEvents = new FilteredList<>(this.remindMe.getEventList());
    }

    public ModelManager() {
        this(new RemindMe(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
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
    public Path getRemindMeFilePath () {
        return userPrefs.getRemindMeFilePath();
    }

    @Override
    public void setRemindMeFilePath(Path remindMeFilePath) {
        requireNonNull(remindMeFilePath);
        userPrefs.setRemindMeFilePath(remindMeFilePath);
    }

    //=========== RemindMe Person ================================================================================


    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return remindMe.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        remindMe.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        remindMe.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        remindMe.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedRemindMe}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return remindMe.equals(other.remindMe)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredModules.equals(other.filteredModules)
                && filteredEvents.equals(other.filteredEvents);
    }

    //=========== RemindMe =============================================================

    @Override
    public void setRemindMe(RemindMe remindMe) {
        this.remindMe.resetData(remindMe);
    }

    @Override
    public void resetModules() {
        this.remindMe.resetModules();
    }

    @Override
    public void resetPersons() {
        this.remindMe.resetPersons();
    }

    @Override
    public void resetEvents() {
        this.remindMe.resetEvents();
    }

    @Override
    public ReadOnlyRemindMe getRemindMe() {
        return remindMe;
    }

    @Override
    public void updateFilteredModuleList(Predicate<Module> predicate) {
        requireNonNull(predicate);
        filteredModules.setPredicate(predicate);
    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return filteredModules;
    }

    @Override
    public void updateFilteredEventList(Predicate<GeneralEvent> predicate) {
        requireNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }

    @Override
    public ObservableList<GeneralEvent> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return remindMe.hasModule(module);
    }

    @Override
    public boolean hasModule(int index) {
        return remindMe.hasModule(index);
    }

    @Override
    public void addModule(Module module) {
        requireNonNull(module);
        remindMe.addModule(module);
        updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
    }

    @Override
    public void deleteModule(Module target) {
        remindMe.removeModule(target);
    }

    @Override
    public Module getModule(Module module) {
        return remindMe.getModule(module);
    }

    @Override
    public Module getModule(int index) {
        return remindMe.getModule(index);
    }

    @Override
    public void editModule(int index, Title title) {
        requireNonNull(title);
        remindMe.editModule(index, title);
    }

    @Override
    public void setModule(Module target, Module editedMod) {
        requireAllNonNull(target, editedMod);
        remindMe.setModule(target, editedMod);
    }

    @Override
    public boolean hasAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        return remindMe.hasAssignment(module, assignment);
    }

    @Override
    public boolean hasAssignment(Module module, int index) {
        requireNonNull(module);
        return remindMe.hasAssignment(module, index);
    }

    @Override
    public void addAssignment(Module module, Assignment assignment) {
        requireAllNonNull(module, assignment);
        remindMe.addAssignment(module, assignment);
    }

    @Override
    public void editAssignment(Module module, int index, Description edit) {
        requireNonNull(module);
        remindMe.editAssignment(module, index, edit);
    }

    @Override
    public void editAssignment(Module module, int index, LocalDateTime edit) {
        requireNonNull(module);
        remindMe.editAssignment(module, index, edit);
    }

    @Override
    public boolean hasExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        return remindMe.hasExam(module, exam);
    }

    @Override
    public boolean hasExam(Module module, int index) {
        requireNonNull(module);
        return remindMe.hasExam(module, index);
    }

    @Override
    public void editExam(Module module, int index, LocalDateTime edit) {
        requireAllNonNull(module, edit);
        remindMe.editExam(module, index, edit);
    }

    @Override
    public void addExam(Module module, Exam exam) {
        requireAllNonNull(module, exam);
        remindMe.addExam(module, exam);
    }

    @Override
    public boolean hasEvent(GeneralEvent event) {
        requireNonNull(event);
        return remindMe.hasEvent(event);
    }

    @Override
    public boolean hasEvent(int index) {
        return remindMe.hasEvent(index);
    }

    @Override
    public void editEvent(int index, Description edit) {
        remindMe.editEvent(index, edit);
    }

    @Override
    public void editEvent(int index, LocalDateTime edit) {
        remindMe.editEvent(index, edit);
    }

    @Override
    public void deleteEvent(GeneralEvent target) {
        remindMe.removeEvent(target);
    }

    @Override
    public void addEvent(GeneralEvent event) {
        requireNonNull(event);
        remindMe.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public GeneralEvent getEvent(int index) {
        return remindMe.getEvent(index);
    }

}
