package seedu.booking.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Booking> PREDICATE_SHOW_ALL_BOOKINGS = unused -> true;

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Venue> PREDICATE_SHOW_ALL_VENUES = unused -> true;

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
    Path getBookingSystemFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setBookingSystemFilePath(Path bookingSystemFilePath);

    /**
     * Replaces address book data with the data in {@code bookingSystem}.
     */
    void setBookingSystem(ReadOnlyBookingSystem bookingSystem);

    /**
     * Returns the BookingSystem
     */
    ReadOnlyBookingSystem getBookingSystem();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with the same email as {@code person} exists in the address book.
     */
    boolean hasPersonWithEmail(Email email);


    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);



    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);



    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Returns an unmodifiable view of the upcoming booking list
     */
    ObservableList<Booking> getUpcomingBookingList();

    /**
     * Returns an unmodifiable view of the filtered booking list
     */
    ObservableList<Booking> getFilteredBookingList();

    /**
     * Returns an unmodifiable view of the filtered venue list
     */
    ObservableList<Venue> getFilteredVenueList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /**
     * Updates the filter of the filtered venue list to filter by the given {@code predicate}.
     *
     * @param predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredVenueList(Predicate<Venue> predicate);


    /**
     * Updates the filter of the filtered booking list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredBookingList(Predicate<Booking> predicate);

    /**
     * Returns true if a venue with the same name as {@code venue} exists in the system.
     */
    boolean hasVenue(Venue venue);

    /**
     * Deletes the given venue.
     * The venue must exist in the system.
     */
    void deleteVenue(Venue target);

    /**
     * Returns true if a venue with the same name as {@code venue} exists in the system.
     */
    boolean hasVenueWithVenueName(VenueName venueName);

    /**
     * Adds the given venue.
     * {@code venue} must not already exist in the system.
     */
    void addVenue(Venue venue);

    /**
     * Replaces the given venue {@code target} with {@code editedVenue}.
     * {@code target} must exist in the booking system.
     * The venue identity of {@code editedVenue} must not be the same as another existing venue in the booking system.
     */
    void setVenue(Venue target, Venue editedVenue);

    /// logic related to bookings

    /**
     * Deletes the given booking.
     * The booking must exist in the system.
     * @param bookingId
     */
    void deleteBooking(Id bookingId);


    /**
     * Returns true if a booking with the same identity as {@code booking} exists in the address book.
     */
    boolean hasBooking(Booking booking);

    /**
     * Adds the given booking.
     * {@code booking} must not already exist in the address book.
     */
    void addBooking(Booking booking);


    /**
     * Replaces the given booking {@code target} with {@code editedBooking}.
     * {@code target} must exist in the booking system.
     * The booking identity of {@code editedBooking} must not be the same
     * as another existing booking in the booking system.
     */
    void setBooking(Booking target, Booking editedBooking);
}
