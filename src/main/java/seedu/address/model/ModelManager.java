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

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final Sochedule sochedule;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Event> filteredEvents;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        // initialised to null to maintain original functionality temporarily
        this.sochedule = null;
        filteredTasks = null;
        filteredEvents = null;
    }

    /**
     * Initializes a ModelManager with the given sochedule and userPrefs.
     */
    public ModelManager(ReadOnlySochedule sochedule, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(sochedule, userPrefs);

        logger.fine("Initializing with SOChedule: " + sochedule + " and user prefs " + userPrefs);

        this.sochedule = new Sochedule(sochedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.sochedule.getTaskList());
        filteredEvents = new FilteredList<>(this.sochedule.getEventList());

        // initialised to null to maintain original functionality temporarily
        this.addressBook = null;
        filteredPersons = null;
    }

    // original function to be replaced once Sochedule support is complete
    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    /*
    public ModelManager() {
        this(new Sochedule(), new UserPrefs());
    }
    */

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
        // null check is temporary during transition to new data
        ModelManager other = (ModelManager) obj;
        return (addressBook == null || addressBook.equals(other.addressBook)) //to be removed
                && userPrefs.equals(other.userPrefs)
                && (filteredPersons == null || filteredPersons.equals(other.filteredPersons)) //to be removed
                && (sochedule == null || sochedule.equals(other.sochedule))
                && (filteredTasks == null || filteredTasks.equals(other.filteredTasks))
                && (filteredEvents == null || filteredEvents.equals(other.filteredEvents));
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
    // Functions of the original AB3 - will be deleted once transition over to new data structure

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================
    // Functions of the original AB3 - will be deleted once transition over to new data structure
    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
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

    //=========== event ==================================================================================

    @Override
    public Path getEventListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setEventListFilePath(Path eventListFilePath) {
        requireAllNonNull(eventListFilePath);
        userPrefs.setEventListFilePath(eventListFilePath);
    }

    @Override
    public void setEventList(ReadOnlySochedule sochedule) {
        this.sochedule.resetEventData(sochedule);
    }

    @Override
    public ReadOnlySochedule getEventList() {
        return sochedule;
    }

    @Override
    public boolean hasEvent(Event event) {
        requireAllNonNull(event);
        return sochedule.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        sochedule.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        sochedule.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        sochedule.setEvent(target, editedEvent);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireAllNonNull(predicate);
        filteredEvents.setPredicate(predicate);
    }
}
