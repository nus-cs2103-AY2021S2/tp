package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.AddressBookSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTimeComparator;
import seedu.address.model.contact.Contact;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final AppointmentBook appointmentBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Appointment> filteredAppointments;
    private final FilteredList<Contact> filteredContacts;
    private Predicate<Contact> contactFilter;
    private Predicate<Appointment> appointmentFilter;

    /**
     * Initializes a ModelManager with the given addressBook, appointmentBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyAppointmentBook appointmentBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, appointmentBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook
                + ", appointment book: " + appointmentBook
                + " and user prefs: " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.appointmentBook = new AppointmentBook(appointmentBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredContacts = new FilteredList<>(this.addressBook.getContactList());
        filteredAppointments = new FilteredList<>(this.appointmentBook.getAppointmentList());
        contactFilter = PREDICATE_SHOW_ALL_CONTACTS;
        appointmentFilter = PREDICATE_SHOW_ALL_APPOINTMENTS;
    }

    public ModelManager() {
        this(new AddressBook(), new AppointmentBook(), new UserPrefs());
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
    public String getTheme() {
        return userPrefs.getTheme();
    }

    @Override
    public void setTheme(String theme) {
        requireNonNull(theme);
        assert(!theme.equals(""));
        userPrefs.setTheme(theme);
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
    public AddressBookSettings getAddressBookSettings() {
        return userPrefs.getAddressBookSettings();
    }

    @Override
    public void setAddressBookSettings(AddressBookSettings addressBookSettings) {
        requireNonNull(addressBookSettings);
        userPrefs.setAddressBookSettings(addressBookSettings);
    }

    @Override
    public Comparator<Contact> getAddressBookComparator() {
        return userPrefs.getAddressBookComparator();
    }

    @Override
    public void setAddressBookComparator(String comparator) {
        userPrefs.setAddressBookComparator(comparator);
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
    public boolean hasContact(Contact contact) {
        requireNonNull(contact);
        return addressBook.hasContact(contact);
    }

    @Override
    public void deleteContact(Contact target) {
        addressBook.removeContact(target);
    }

    @Override
    public void addContact(Contact contact) {
        addressBook.addContact(contact);
        updateFilteredContactList(PREDICATE_SHOW_ALL_CONTACTS);
        orderContacts();
    }

    @Override
    public void setContact(Contact target, Contact editedContact) {
        requireAllNonNull(target, editedContact);

        addressBook.setContact(target, editedContact);
        orderContacts();
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        addressBook.setContacts(contacts);
    }

    //=========== Filtered Contact List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code Contact} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return filteredContacts;
    }

    @Override
    public void updateFilteredContactList(Predicate<Contact> predicate) {
        requireNonNull(predicate);

        contactFilter = predicate;

        filterContactList();
    }

    private void filterContactList() {
        filteredContacts.setPredicate(contactFilter);
    }

    //=========== Sorted Contact List Accessors =============================================================

    @Override
    public void sortContactList(String comparator) {
        requireNonNull(comparator);
        setAddressBookComparator(comparator);

        orderContacts();
    }

    @Override
    public void orderContacts() {
        ObservableList<Contact> contactList = addressBook.getContactList();
        SortedList<Contact> sortedContactList = contactList.sorted(getAddressBookComparator());

        setContacts(sortedContactList);
        filterContactList();
    }

    //=========== AppointmentBook ================================================================================

    @Override
    public void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook) {
        this.appointmentBook.resetData(appointmentBook);
    }

    @Override
    public ReadOnlyAppointmentBook getAppointmentBook() {
        return appointmentBook;
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
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentBook.hasAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentBook.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentBook.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        orderAppointments(); // ensure that appointments are loaded in increasing date order
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentBook.setAppointment(target, editedAppointment);
        orderAppointments(); // ensure that appointments are loaded in increasing date order
    }

    @Override
    public void setAppointments(List<Appointment> appointments) {
        appointmentBook.setAppointments(appointments);
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

        appointmentFilter = predicate;

        filterAppointmentList();
    }

    private void filterAppointmentList() {
        filteredAppointments.setPredicate(appointmentFilter);
    }

    //=========== Sorted Appointment List =============================================================

    @Override
    public void orderAppointments() {
        ObservableList<Appointment> appointmentList = appointmentBook.getAppointmentList();
        SortedList<Appointment> sortedAppointmentList = appointmentList.sorted(new DateTimeComparator());

        setAppointments(sortedAppointmentList);
        filterAppointmentList();
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
                && appointmentBook.equals(other.appointmentBook)
                && userPrefs.equals(other.userPrefs)
                && filteredContacts.equals(other.filteredContacts)
                && filteredAppointments.equals(other.filteredAppointments);
    }

}
