package seedu.booking.model;

import javafx.collections.ObservableList;
import seedu.booking.model.booking.Booking;
import seedu.booking.model.person.Person;
import seedu.booking.model.venue.Venue;

/**
 * Unmodifiable view of an booking system state
 */
public interface ReadOnlyBookingSystem {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
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
