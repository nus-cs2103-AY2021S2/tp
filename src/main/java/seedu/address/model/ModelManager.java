package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventStatus;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final EventBook eventBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Event> filteredEvent;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs, ReadOnlyEventBook eventBook) {
        super();
        requireAllNonNull(addressBook, userPrefs, eventBook);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs
            + "event book: " + eventBook);

        this.addressBook = new AddressBook(addressBook);
        this.eventBook = new EventBook(eventBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredEvent = new FilteredList<>(this.eventBook.getEventList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new EventBook());
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

    @Override
    public Path getEventBookFilePath() {
        return userPrefs.getEventBookFilePath();
    }

    @Override
    public void setEventBookFilePath(Path eventBookFilePath) {
        requireNonNull(eventBookFilePath);
        userPrefs.setEventBookFilePath(eventBookFilePath);
    }

    //=========== AddressBook ================================================================================

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

    //=========== EventBook ==================================================================================

    @Override
    public void setEventBook(ReadOnlyEventBook eventBook) {
        this.eventBook.resetData(eventBook);
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return eventBook;
    }

    @Override
    public Optional<Event> getEventByIdentifier(int identifier) {
        return eventBook.getEventByIdentifier(identifier);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return eventBook.hasEvent(event);
    }

    @Override
    public void deleteEvent(Event target) {
        eventBook.removeEvent(target);
    }

    @Override
    public void addEvent(Event event) {
        eventBook.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);

        eventBook.setEvent(target, editedEvent);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code versionedEventBook}
     */
    @Override
    public ObservableList<Event> getFilteredEventList() {
        return filteredEvent;
    }

    /**
     * Helper method that takes in an EventStatus and returns EventIndexPair of events in the current
     * filtered list that matches up with the event
     * @param status status of events to filter for
     * @return Pair of Integer index and Event of each status
     */
    @Override
    public FilteredList<Event> getFilteredListByStatus(EventStatus status) {
        return filteredEvent.filtered(event -> event.getStatus() == status);
    }

    /**
     * Filter list of all events by getting backlog events only
     * @return events with eventStatus of EventStatus.BACKLOG
     */
    @Override
    public FilteredList<Event> getFilteredBacklogList() {
        return getFilteredListByStatus(EventStatus.BACKLOG);
    }

    /**
     * Filter list of all events by getting todo events only
     * @return events with eventStatus of EventStatus.TODO
     */
    @Override
    public FilteredList<Event> getFilteredTodoList() {
        return getFilteredListByStatus(EventStatus.TODO);
    }

    /**
     * Filter list of all events by getting in progress events only
     * @return events with eventStatus of EventStatus.IN_PROGRESS
     */
    @Override
    public FilteredList<Event> getFilteredInProgressList() {
        return getFilteredListByStatus(EventStatus.IN_PROGRESS);
    }

    /**
     * Filter list of all events by getting done events only
     * @return events with eventStatus of EventStatus.DONE
     */
    @Override
    public FilteredList<Event> getFilteredDoneList() {
        return getFilteredListByStatus(EventStatus.DONE);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireAllNonNull(predicate);
        filteredEvent.setPredicate(predicate);
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
        return addressBook.equals(other.addressBook)
                && eventBook.equals(other.eventBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons)
                && filteredEvent.equals(other.filteredEvent);
    }

}
