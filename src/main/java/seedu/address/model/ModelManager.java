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
import seedu.address.model.person.Patient;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private Patient selectedPatient;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.addressBook.getPersonList());
        // initialise to only show main persons (no archived patients)
        updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
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
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Patient patient) {
        requireNonNull(patient);
        return addressBook.hasPerson(patient);
    }

    @Override
    public void deletePerson(Patient target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Patient patient) {
        addressBook.addPerson(patient);
        updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
    }

    @Override
    public void setPerson(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);
        addressBook.setPerson(target, editedPatient);
        if (target.equals(this.selectedPatient)) {
            selectPatient(editedPatient);
        }
    }

    @Override
    public void archivePerson(Patient target) {
        requireNonNull(target);
        addressBook.archivePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_ARCHIVED_PATIENTS);
    }

    @Override
    public void unarchivePerson(Patient target) {
        requireNonNull(target);
        addressBook.unarchivePerson(target);
        updateFilteredPersonList(PREDICATE_SHOW_MAIN_PATIENTS);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Patient> getFilteredPersonList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public void sortFilteredPersonList(Comparator<Patient> comparator) {
        requireNonNull(comparator);
        filteredPatients.sort(comparator);
    }

    @Override
    public void selectPatient(Patient patient) {
        this.selectedPatient = patient;
    }

    @Override
    public Patient getSelectedPatient() {
        return this.selectedPatient;
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
                && userPrefs.equals(other.userPrefs)
                && filteredPatients.equals(other.filteredPatients);
    }

}
