package seedu.booking.model;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.booking.NonOverlappingBookingList;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.Phone;
import seedu.booking.model.person.UniquePersonList;
import seedu.booking.model.venue.UniqueVenueList;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;

/**
 * Wraps all data at the booking-system level
 * Duplicates and overlaps are not allowed (by .isSame/isOverlapping comparison)
 */
public class BookingSystem implements ReadOnlyBookingSystem {

    private final UniquePersonList persons;
    private final NonOverlappingBookingList bookings;
    private final UniqueVenueList venues;


    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();

        bookings = new NonOverlappingBookingList();

        venues = new UniqueVenueList();

    }

    public BookingSystem() {}

    /**
     * Creates an BookingSystem using the Persons in the {@code toBeCopied}
     */
    public BookingSystem(ReadOnlyBookingSystem toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }


    /**
     * Resets the existing data of this {@code BookingSystem} with {@code newData}.
     */
    public void resetData(ReadOnlyBookingSystem newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setVenues(newData.getVenueList());
        setBookings(newData.getBookingList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the booking system.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Adds a person to the booking system.
     * The person must not already exist in the booking system.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }


    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the booking system.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the booking system.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code BookingSystem}.
     * {@code key} must exist in the booking system.
     */
    public void removePerson(Person key) {
        persons.remove(key);
        bookings.removeBookingWithBooker(key.getEmail());
    }

    @Override
    public ObservableList<Booking> getBookingList() {
        return bookings.asUnmodifiableObservableList();
    }


    /**
     * Returns true if a booking with the same identity as {@code booking} exists in the booking system.
     */
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookings.contains(booking);
    }

    /**
     * Adds a booking to the booking system.
     * The booking must not already exist in the booking system.
     */
    public void addBooking(Booking b) {
        bookings.add(b);
    }

    //// booking-level operations
    /**
     * Removes {@code key} from this {@code BookingSystem}.
     * {@code key} must exist in the booking system.
     */
    public void removeBooking(Booking key) {
        bookings.remove(key);
    }

    /**
     * Replaces the contents of the venue list with {@code venues}.
     * {@code venues} must not contain duplicate venues.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setBookings(bookings);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Venue> getVenueList() {
        return venues.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingSystem // instanceof handles nulls
                && persons.equals(((BookingSystem) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }




    /**
     * Returns true if a venue with the same identity as {@code venue} exists in the system.
     */
    public boolean hasVenue(Venue venue) {
        requireNonNull(venue);
        return venues.contains(venue);
    }

    /**
     * Adds a venue to the system.
     * The venue must not already exist in the system.
     */
    public void addVenue(Venue v) {
        venues.add(v);
    }

    /**
     * Replaces the contents of the venue list with {@code venues}.
     * {@code venues} must not contain duplicate venues.
     */
    public void setVenues(List<Venue> venues) {
        this.venues.setVenues(venues);
    }

    /**
     * Replaces the given venue {@code target} in the list with {@code editedVenue}.
     * {@code target} must exist in the booking system.
     * The venue identity of {@code editedVenue} must not be the same as another existing venue in the booking system.
     */
    public void setVenue(Venue target, Venue editedVenue) {
        requireNonNull(editedVenue);
        venues.setVenue(target, editedVenue);
    }

    /**
     * Removes {@code key} from this {@code BookingSystem}.
     * {@code key} must exist in the booking system.
     */
    public void removeVenue(Venue key) {
        bookings.removeBookingWithVenue(key.getVenueName());
        venues.remove(key);
    }


    /**
     * Returns true if a person with the same email as {@code email} exists in the system.
     */
    public boolean hasPersonWithEmail(Email email) {
        requireNonNull(email);
        return persons.containsEmail(getPersonList(), email);
    }

    /**
     * Returns true if a person with the same phone as {@code email} exists in the system.
     */
    public boolean hasPersonWithPhone(Phone phone) {
        requireNonNull(phone);
        return persons.containsPhone(getPersonList(), phone);
    }


    /**
     * Returns true if a venue with the same name as {@code venueName} exists in the system.
     */
    public boolean hasVenueWithVenueName(VenueName venueName) {
        requireNonNull(venueName);
        return venues.containsVenueName(getVenueList(), venueName);
    }

    /**
     * Replaces the given venue {@code target} in the list with {@code editedVenue}.
     * {@code target} must exist in the booking system.
     * The venue identity of {@code editedVenue} must not be the same as another existing venue in the booking system.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireNonNull(editedBooking);
        bookings.setBooking(target, editedBooking);
    }

    /**
     * Replaces the old venue name {@code oldVenueName} in the booking with {@code newVenueName}.
     * {@code oldVenueName} must exist in the booking system.
     */
    public void updateVenueInBookings(VenueName oldVenueName, VenueName newVenueName) {
        requireAllNonNull(oldVenueName, newVenueName);
        bookings.updateVenueInBookings(oldVenueName, newVenueName);
    }

    /**
     * Replaces the old person email {@code oldEmail} in the booking with {@code newEmail}.
     * {@code oldEmail} must exist in the booking system.
     */
    public void updatePersonInBookings(Email oldEmail, Email newEmail) {
        requireAllNonNull(oldEmail, newEmail);
        bookings.updatePersonInBookings(oldEmail, newEmail);
    }

    /**
     * Returns true if a booking with the overlapped duration as {@code toAdd} exists in the system.
     */
    public boolean hasOverlappedBooking(Booking toAdd) {
        return bookings.overlaps(toAdd);
    }

    public boolean hasMoreThanOneOverlappedBooking(Booking toAdd) {
        return bookings.countOverlaps(toAdd) > 1;
    }
}
