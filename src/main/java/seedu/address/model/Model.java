package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.Patient;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that only shows patients in the main list
     */
    Predicate<Patient> PREDICATE_SHOW_MAIN_PATIENTS = patient -> !patient.isArchived();

    /**
     * {@code Predicate} that only shows archived patients
     */
    Predicate<Patient> PREDICATE_SHOW_ARCHIVED_PATIENTS = Patient::isArchived;

    /**
     * {@code Predicate} that only shows patients with appointments
     */
    Predicate<Patient> PREDICATE_SHOW_PATIENTS_WITH_APPT =
        patient -> patient.getAppointments().size() > 0;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the address book.
     */
    boolean hasPerson(Patient patient);

    /**
     * Deletes the given patient.
     * The patient must exist in the address book.
     */
    void deletePerson(Patient target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void addPerson(Patient patient);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient
     * in the address book.
     */
    void setPerson(Patient target, Patient editedPatient);

    /**
     * Archives the given patient.
     * {@code patient} must not already exist in the archives of address book.
     */
    void archivePerson(Patient patient);

    /**
     * Unarchives the given patient.
     * {@code patient} must not already exist in the address book.
     */
    void unarchivePerson(Patient patient);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Patient> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Patient> predicate);

    /**
     * Sorts the filtered person list to sort by the given {@code comparator}.
     *
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredPersonList(Comparator<Patient> comparator);

    /**
     * Selects a patient to be the selected patient in the patient box.
     */
    void selectPatient(Patient patient);

    /**
     * Gets the selected patient in the patient box.
     */
    Patient getSelectedPatient();
}
