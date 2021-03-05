package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

public class SocModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;
    private final Sochedule sochedule;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Event> filteredEvents;

    /**
     * Initializes a SocModelManager with the given sochedule and userPrefs.
     */
    public SocModelManager(ReadOnlySochedule sochedule, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(sochedule, userPrefs);

        logger.fine("Initializing with SOChedule: " + sochedule + " and user prefs " + userPrefs);

        this.sochedule = new Sochedule(sochedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.sochedule.getTaskList());
        filteredEvents = new FilteredList<>(this.sochedule.getEventList());
    }


    public SocModelManager() {
        this(new Sochedule(), new UserPrefs());
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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }


    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {

    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return null;
    }

    @Override
    public boolean hasPerson(Person person) {
        return false;
    }

    @Override
    public void deletePerson(Person target) {

    }

    @Override
    public void addPerson(Person person) {

    }

    @Override
    public void setPerson(Person target, Person editedPerson) {

    }

    //=========== Filtered Person List Accessors =============================================================

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return null;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {

    }

    //=========== task ==================================================================================

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireAllNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
    }

    @Override
    public void setTaskList(ReadOnlySochedule sochedule) {
        this.sochedule.resetTaskData(sochedule);
    }

    @Override
    public ReadOnlySochedule getTaskList() {
        return sochedule;
    }

    @Override
    public boolean hasTask(Task task) {
        requireAllNonNull(task);
        return sochedule.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        sochedule.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        sochedule.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        sochedule.setTask(target, editedTask);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireAllNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof SocModelManager)) {
            return false;
        }

        // state check
        SocModelManager other = (SocModelManager) obj;
        return sochedule.equals(other.sochedule)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }

    //=========== event ==================================================================================

    @Override
    public Path getEventListFilePath() {
        return null;
    }

    @Override
    public void setEventListFilePath(Path eventListFilePath) {

    }

    @Override
    public void setEventList(ReadOnlySochedule sochedule) {

    }

    @Override
    public ReadOnlySochedule getEventList() {
        return null;
    }

    @Override
    public boolean hasEvent(Event event) {
        return false;
    }

    @Override
    public void deleteEvent(Event target) {

    }

    @Override
    public void addEvent(Event event) {

    }

    @Override
    public void setEvent(Event target, Event editedEvent) {

    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return null;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {

    }

}
