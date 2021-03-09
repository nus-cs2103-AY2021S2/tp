package seedu.address.model;

import javafx.collections.ObservableList;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Venue;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
<<<<<<< HEAD
     * Returns an unmodifiable view of the bookings list.
     * This list will not contain any duplicate bookings.
     */
    ObservableList<Booking> getBookingList();
    /**
     * Returns an unmodifiable view of the venues list.
     * This list will not contain any duplicate venues.
     */
    ObservableList<Venue> getVenueList();

}
