package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.property.Property;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Property> PREDICATE_SHOW_ALL_PROPERTIES = unused -> true;
    Predicate<Appointment> PREDICATE_SHOW_ALL_APPOINTMENTS = unused -> true;

    // =====  UserPrefs ==========================================================================================

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

    // =====  PropertyBook =======================================================================================

    /**
     * Returns the user prefs' property book file path.
     */
    Path getPropertyBookFilePath();

    /**
     * Sets the user prefs' property book file path.
     */
    void setPropertyBookFilePath(Path propertyBookFilePath);

    /**
     * Replaces property book data with the data in {@code propertyBook}.
     */
    void setPropertyBook(ReadOnlyPropertyBook propertyBook);

    /**
     * Returns the PropertyBook
     */
    ReadOnlyPropertyBook getPropertyBook();

    /**
     * Returns true if a property with the same identity as {@code property} exists in the property book
     */
    boolean hasProperty(Property property);

    /**
     * Deletes the given property.
     * The property must exist in the property book.
     */
    void deleteProperty(Property target);

    /**
     * Adds the given property to the property book.
     */
    void addProperty(Property property);

    /**
     * Replaces the given property {@code target} with {@code editedProperty}.
     * {@code target} must exist in the property book.
     * The property identity of {@code editedProperty} must not be the same as another existing property
     * in the property book.
     */
    void setProperty(Property target, Property editedProperty);

    /**
     * Replaces the property at index {@code i} in the property book with {@code editedProperty}.
     * {@code i} must be a valid index in the property book.
     * The property identity of {@code editedProperty} must not be the same as another existing property
     * in the property book.
     */
    void setProperty(int i, Property property);

    /**
     * Returns the property at index {@code i} in the property book.
     * {@code i} must be a valid index in the property book.
     */
    Property getProperty(int i);

    /**
     * Returns an unmodifiable view of the filtered property list
     */
    ObservableList<Property> getFilteredPropertyList();

    /**
     * Updates the filter of the filtered property list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPropertyList(Predicate<Property> predicate);

    /**
     * Returns the total number of properties in the property book.
     */
    int getPropertyListSize();

    /**
     * Sorts the the filtered property list by the given {@code comparator}.
     */
    void sortPropertyList(Comparator<Property> comparator);

    // =====  AppointmentBook ====================================================================================

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

    /**
     * Returns the AppointmentBook
     */
    ReadOnlyAppointmentBook getAppointmentBook();

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the appointment book
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes the given appointment.
     * The appointment must exist in the appointment book.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds the given appointment to the appointment book.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces the given appointment {@code target} with {@code editedAppointment}.
     * {@code target} must exist in the appointment book.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing appointment
     * in the appointment book.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    /**
     * Returns the appointment at index {@code i} in the appointment book.
     * {@code i} must be a valid index in the appointment book.
     */
    Appointment getAppointment(int i);

    /**
     * Returns an unmodifiable view of the filtered appointment list
     */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Updates the filter of the filtered appointment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    /**
     * Returns the total number of appointments in the appointment book.
     */
    int getAppointmentListSize();

    /**
     * Sorts the the filtered appointment list by the given {@code comparator}.
     */
    void sortAppointmentList(Comparator<Appointment> comparator);

}
