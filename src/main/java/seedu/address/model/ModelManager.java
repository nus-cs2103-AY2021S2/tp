package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger LOGGER = LogsCenter.getLogger(ModelManager.class);

    private final AppointmentSchedule appointmentSchedule;
    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Appointment> filteredAppointments;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAppointmentSchedule appointmentSchedule, ReadOnlyAddressBook addressBook,
                        ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(appointmentSchedule, addressBook, userPrefs);

        LOGGER.fine("Initializing with appointment schedule: " + appointmentSchedule
                + ", address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.appointmentSchedule = new AppointmentSchedule(appointmentSchedule);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredAppointments = new FilteredList<>(this.appointmentSchedule.getAppointmentList());
    }

    public ModelManager() {
        this(new AppointmentSchedule(), new AddressBook(), new UserPrefs());
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

    //=========== AddressBook ================================================================================

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
    public ObservableList<String> getFilteredDoctorList() {
        // TODO: update to Person or Doctor Class
        final ObservableList<String> internalList = FXCollections.observableArrayList();
        internalList.add("Coming Soon!");
        internalList.add("More Coming Soon!");
        internalList.add("Even More Coming Soon!");
        internalList.add("Much More Coming Soon!");
        final ObservableList<String> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
        final ObservableList<String> filteredDoctors = new FilteredList<>(internalUnmodifiableList);
        return filteredDoctors;

    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== AppointmentSchedule ========================================================================
    @Override
    public Path getAppointmentScheduleFilePath() {
        return userPrefs.getAppointmentScheduleFilePath();
    }

    @Override
    public void setAppointmentScheduleFilePath(Path appointmentScheduleFilePath) {
        requireNonNull(appointmentScheduleFilePath);
        userPrefs.setAppointmentScheduleFilePath(appointmentScheduleFilePath);
    }

    @Override
    public void setAppointmentSchedule(ReadOnlyAppointmentSchedule appointmentSchedule) {
        this.appointmentSchedule.resetData(appointmentSchedule);
    }

    @Override
    public ReadOnlyAppointmentSchedule getAppointmentSchedule() {
        return appointmentSchedule;
    }

    @Override
    public boolean hasConflictingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointmentSchedule.hasConflict(appointment);
    }

    @Override
    public boolean hasConflictingAppointmentExcludingTarget(Appointment target, Appointment appointment) {
        requireNonNull(appointment);
        return appointmentSchedule.hasConflictExcludingTarget(target, appointment);
    }

    @Override
    public void deleteAppointment(Appointment target) {
        appointmentSchedule.removeAppointment(target);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        appointmentSchedule.addAppointment(appointment);
        updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
    }

    @Override
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        appointmentSchedule.setAppointment(target, editedAppointment);
    }

    //=========== Filtered Appointment List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Appointment} backed by the internal list of
     * {@code versionedAppointmentSchedule}
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
        return appointmentSchedule.equals(other.appointmentSchedule)
                && addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }
}
