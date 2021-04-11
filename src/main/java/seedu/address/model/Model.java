package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.AddressBookSettings;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.contact.Contact;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Contact> PREDICATE_SHOW_ALL_CONTACTS = unused -> true;

    /** {@code Predicate} that evaluates to true if a contact is favourited */
    Predicate<Contact> PREDICATE_SHOW_FAVOURITES = contact -> contact.getFavourite().isFav();

    //=========== UserPrefs ==================================================================================

    /** {@code Predicate} that always evaluate to true */
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

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
     * Returns the theme in user pref's GUI settings.
     */
    String getTheme();

    /**
     * Sets the theme in user pref's GUI settings.
     */
    void setTheme(String theme);

    /**
     * Returns the user prefs' Address Book settings.
     */
    AddressBookSettings getAddressBookSettings();

    /**
     * Sets the user prefs' Address Book settings.
     */
    void setAddressBookSettings(AddressBookSettings addressBookSettings);

    /**
     * Returns the user prefs' Address Book Comparator.
     */
    Comparator<Contact> getAddressBookComparator();

    /**
     * Sets the user prefs' Address Book Comparator.
     */
    void setAddressBookComparator(String comparator);

    //=========== AddressBook ================================================================================

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

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    //=========== Contact ================================================================================

    /**
     * Returns true if a contact with the same identity as {@code contact} exists in the address book.
     */
    boolean hasContact(Contact contact);

    /**
     * Deletes the given contact.
     * The contact must exist in the address book.
     */
    void deleteContact(Contact target);

    /**
     * Adds the given contact.
     * {@code contact} must not already exist in the address book.
     */
    void addContact(Contact contact);

    /**
     * Replaces the given contact {@code target} with {@code editedContact}.
     * {@code target} must exist in the address book.
     * The contact identity of {@code editedContact} must not be the same as
     * another existing contact in the address book.
     */
    void setContact(Contact target, Contact editedContact);

    /**
     * Sets the contact list to the given {@code contacts}.
     */
    void setContacts(List<Contact> contacts);

    /** Returns an unmodifiable view of the filtered contact list */
    ObservableList<Contact> getFilteredContactList();

    /**
     * Updates the filter of the filtered contact list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredContactList(Predicate<Contact> predicate);

    /**
     * Updates the contact list to a sorted list sorted by the given {@code comaparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortContactList(String comparator);

    /** Orders contact list according to saved order.
     *  Order can be set using {@code sortContactList}.
     * */
    void orderContacts();

    //=========== AppointmentBook ================================================================================

    /**
     * Returns the user prefs' appointment book file path.
     */
    Path getAppointmentBookFilePath();

    /**
     * Sets the user prefs' appointment book file path.
     */
    void setAppointmentBookFilePath(Path appointmentBookFilePath);

    /**
     * Replaces appointment book data with the data in {@code appointmentBook}.
     */
    void setAppointmentBook(ReadOnlyAppointmentBook appointmentBook);

    /** Returns the AppointmentBook */
    ReadOnlyAppointmentBook getAppointmentBook();

    //=========== Appointment ================================================================================

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment book.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the appointment book.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment.
     * {@code appointment} must not already exist in the appointment book.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The contact identity of {@code editedAppointment} must not be the same as another existing appointment
     * in the address book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    void setAppointments(List<Appointment> appointments);

    /** Returns an unmodifiable view of the filtered appointment list */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /** Orders appointment list in increasing DateTime order. */
    void orderAppointments();
}
