package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.property.Property;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final FilteredList<Person> filteredPersons;

    private final PropertyBook propertyBook;
    private final FilteredList<Property> filteredProperties;

    private final UserPrefs userPrefs;
    private final AppointmentBook appointmentBook;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAppointmentBook appointmentBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(appointmentBook, userPrefs);

        logger.fine("Initializing with appointment book: " + appointmentBook
                + " and user prefs " + userPrefs);

        addressBook = new AddressBook();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        this.propertyBook = new PropertyBook();
        filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());

        this.userPrefs = new UserPrefs(userPrefs);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
    }

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

        propertyBook = new PropertyBook();
        filteredProperties = new FilteredList<>(propertyBook.getPropertyList());
        appointmentBook = new AppointmentBook();
        filteredAppointments = new FilteredList<>(appointmentBook.getAppointmentList());
    }

    /**
     * Initializes a ModelManager with the given propertyBook and userPrefs.
     */
    public ModelManager(ReadOnlyPropertyBook propertyBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(propertyBook, userPrefs);

        logger.fine("Initializing with address book: " + propertyBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook();
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        this.propertyBook = new PropertyBook(propertyBook);
        this.filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
        this.appointmentBook = new AppointmentBook();
        this.filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
    }

    /**
     * Initializes a ModelManager with the given appointmentBook, propertyBook and userPrefs.
     */
    public ModelManager(ReadOnlyAppointmentBook appointmentBook, ReadOnlyPropertyBook propertyBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(appointmentBook, propertyBook, userPrefs);

        logger.fine("Initializing with property book: " + propertyBook + ", appointment book: " + appointmentBook
                + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook();
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<>(this.addressBook.getPersonList());

        this.propertyBook = new PropertyBook(propertyBook);
        this.filteredProperties = new FilteredList<>(this.propertyBook.getPropertyList());
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
    }


    public ModelManager() {
        this(new AppointmentBook(), new PropertyBook(), new UserPrefs());
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
    public Path getAppointmentBookFilePath() {
        return userPrefs.getAppointmentBookFilePath();
    }

    @Override
    public void setAppointmentBookFilePath(Path appointmentBookFilePath) {
        requireNonNull(appointmentBookFilePath);
        userPrefs.setAppointmentBookFilePath(appointmentBookFilePath);
    }

    @Override
    public Path getPropertyBookFilePath() {
        return userPrefs.getPropertyBookFilePath();
    }

    @Override
    public void setPropertyBookFilePath(Path propertyBookFilePath) {
        requireNonNull(propertyBookFilePath);
        userPrefs.setPropertyBookFilePath(propertyBookFilePath);
    }

    //=========== PropertyBook ================================================================================

    @Override
    public boolean hasProperty(Property property) {
        requireNonNull(property);
        return propertyBook.hasProperty(property);
    }

    @Override
    public void setPropertyBook(ReadOnlyPropertyBook propertyBook) {
        this.propertyBook.resetData(propertyBook);
    }

    @Override
    public void addProperty(Property property) {
        propertyBook.addProperty(property);
        updateFilteredPropertyList(PREDICATE_SHOW_ALL_PROPERTIES);
    }

    @Override
    public void deleteProperty(Property target) {
        propertyBook.removeProperty(target);
    }

    @Override
    public ReadOnlyPropertyBook getPropertyBook() {
        return propertyBook;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Property> getFilteredPropertyList() {
        return filteredProperties;
    }

    @Override
    public void updateFilteredPropertyList(Predicate<Property> predicate) {
        requireNonNull(predicate);
        filteredProperties.setPredicate(predicate);
    }

    @Override
    public int getPropertySize() {
        return filteredProperties.size();
    }

    @Override
    public Property getProperty(int i) {
        return filteredProperties.get(i);
    }

    @Override
    public void setProperty(int i, Property property) {
        Property target = getProperty(i);
        setProperty(target, property);
    }

    @Override
    public void setProperty(Property target, Property editedProperty) {
        propertyBook.setProperty(target, editedProperty);
    }

    //=========== AppointmentBook =============================================================================

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentBook.hasAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);
        appointmentBook.setAppointment(target, editedAppointment);
    }

    @Override
    public Appointment getAppointment(int i) {
        return appointmentBook.getAppointmentList().get(i);
    }

    @Override
    public int getAppointmentSize() {
        return appointmentBook.getAppointmentList().size();
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentBook.removeAppointment(target);
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return appointmentBook;
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

    //=========== Filtered Person List Accessors =============================================================

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
    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAppointmentBook}
     */
    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return filteredAppointments;
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
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
        return (propertyBook.equals(other.propertyBook)
                && userPrefs.equals(other.userPrefs)
                && filteredProperties.equals(other.filteredProperties))
                && appointmentBook.equals(other.appointmentBook)
                && filteredAppointments.equals(other.filteredAppointments);
    }

    /**
     * Sorts appointment list using the specified comparator {@code comparator}.
     */
    @Override
    public void sortAppointmentList(Comparator<Appointment> comparator) {
        requireNonNull(comparator);
        this.appointmentBook.sortAppointments(comparator);
    }

    /**
     * Sorts property list using the specified comparator {@code comparator}.
     */
    @Override
    public void sortPropertyList(Comparator<Property> comparator) {
        requireNonNull(comparator);
        this.propertyBook.sortProperties(comparator);
    }

}
