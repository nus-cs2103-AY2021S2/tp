package seedu.booking.model;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.booking.commons.core.GuiSettings;
import seedu.booking.commons.core.LogsCenter;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.Id;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final BookingSystem bookingSystem;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Booking> filteredBookings;
    private final FilteredList<Venue> filteredVenues;

    /**
     * Initializes a ModelManager with the given bookingSystem and userPrefs.
     */
    public ModelManager(ReadOnlyBookingSystem bookingSystem, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(bookingSystem, userPrefs);

        logger.fine("Initializing with address book: " + bookingSystem + " and user prefs " + userPrefs);

        this.bookingSystem = new BookingSystem(bookingSystem);
        this.userPrefs = new UserPrefs(userPrefs);

        filteredPersons = new FilteredList<>(this.bookingSystem.getPersonList());
        filteredBookings = new FilteredList<>(this.bookingSystem.getBookingList());
        filteredVenues = new FilteredList<>(this.bookingSystem.getVenueList());
    }

    public ModelManager() {
        this(new BookingSystem(), new UserPrefs());
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
    public Path getBookingSystemFilePath() {
        return userPrefs.getBookingSystemFilePath();
    }

    @Override
    public void setBookingSystemFilePath(Path bookingSystemFilePath) {
        requireNonNull(bookingSystemFilePath);
        userPrefs.setBookingSystemFilePath(bookingSystemFilePath);
    }

    //=========== BookingSystem ================================================================================

    @Override
    public void setBookingSystem(ReadOnlyBookingSystem bookingSystem) {
        this.bookingSystem.resetData(bookingSystem);
    }

    @Override
    public ReadOnlyBookingSystem getBookingSystem() {
        return bookingSystem;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return bookingSystem.hasPerson(person);
    }

    @Override
    public boolean hasPersonWithEmail(Email email) {
        requireNonNull(email);
        return bookingSystem.hasPersonWithEmail(email);
    }

    @Override
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookingSystem.hasBooking(booking);
    }

    @Override
    public void deletePerson(Person target) {
        bookingSystem.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        bookingSystem.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addBooking(Booking booking) {
        bookingSystem.addBooking(booking);
        updateFilteredBookingList(PREDICATE_SHOW_ALL_BOOKINGS);
    }

    @Override
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);
        bookingSystem.setBooking(target, editedBooking);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);
        bookingSystem.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasVenue(Venue venue) {
        requireNonNull(venue);
        return bookingSystem.hasVenue(venue);
    }

    @Override
    public boolean hasVenueWithVenueName(VenueName venueName) {
        requireNonNull(venueName);
        return bookingSystem.hasVenueWithVenueName(venueName);
    }

    @Override
    public void addVenue(Venue venue) {
        bookingSystem.addVenue(venue);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setVenue(Venue target, Venue editedVenue) {
        requireAllNonNull(target, editedVenue);

        bookingSystem.setVenue(target, editedVenue);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedBookingSystem}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Booking} backed by the internal list of
     * {@code versionedBookingSystem}
     */
    @Override
    public ObservableList<Booking> getUpcomingBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
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
        return bookingSystem.equals(other.bookingSystem)
                && userPrefs.equals(other.userPrefs)
                && filteredBookings.equals(other.filteredBookings)
                && filteredPersons.equals(other.filteredPersons);
    }
    //=========== Venue List =============================================================

    @Override
    public void deleteVenue(Venue target) {
        bookingSystem.removeVenue(target);
    }

    //=========== Filtered Venue List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Venues} backed by the internal list of
     * {@code versionedBookCoinToTheMoon}
     */
    @Override
    public ObservableList<Venue> getFilteredVenueList() {
        return filteredVenues;
    }

    @Override
    public void updateFilteredVenueList(Predicate<Venue> predicate) {
        requireNonNull(predicate);
        filteredVenues.setPredicate(predicate);
    }

    //=========== Bookings ================================================================================

    @Override
    public void deleteBooking(Id bookingId) {
        bookingSystem.removeBooking(bookingId);
    }

    //=========== Filtered Booking List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Booking} backed by the internal list of
     * {@code versionedBookingSystem}
     */
    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return filteredBookings;
    }

    @Override
    public void updateFilteredBookingList(Predicate<Booking> predicate) {
        requireNonNull(predicate);
        filteredBookings.setPredicate(predicate);
    }
}
