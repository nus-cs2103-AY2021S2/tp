package seedu.booking.model.booking;

import static java.util.Objects.requireNonNull;
import static seedu.booking.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.booking.commons.util.StringUtil;
import seedu.booking.model.booking.exceptions.BookingNotFoundException;
import seedu.booking.model.booking.exceptions.DuplicateBookingException;
import seedu.booking.model.booking.exceptions.OverlappingBookingException;
import seedu.booking.model.person.Email;
import seedu.booking.model.venue.Venue;
import seedu.booking.model.venue.VenueName;
import seedu.booking.model.venue.exceptions.VenueNotFoundException;

/**
 * A list of bookings that enforces that is it non overlapping between its elements and does not allow nulls.
 * A booking is considered non overlapping by comparing using {@code Booking#isOverlapping(Booking)}.
 * As such, adding and updating of booking uses Booking#isOverlapping(Booking) for checking
 * so as to ensure that the booking being added or updated is non overlapping in terms of booking timing
 * in the NonOverlappingBookingList. However, the removal of a venue uses Venue#equals(Object) so
 * as to ensure that the venue with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Booking#isOverlapping(Booking)
 */
public class NonOverlappingBookingList implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent booking as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }


    /**
     * Returns true if the list contains an overlapping booking as the given argument.
     */
    public boolean overlaps(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isOverlapping);
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookingException();
        }
        if (overlaps(toAdd)) {
            throw new OverlappingBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list.
     * The booking data of {@code editedPerson} must not be the same and not be overlapping
     * as another existing booking in the list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        if (!target.equals(editedBooking) && contains(editedBooking)) {
            throw new DuplicateBookingException();
        }
        if (!target.isOverlapping(editedBooking) && overlaps(editedBooking)) {
            throw new OverlappingBookingException();
        }

        internalList.set(index, editedBooking);
    }

    /**
     * Removes the equivalent booking from the list.
     * The booking must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }


    public void setBookings(NonOverlappingBookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain duplicate persons.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreNonOverlapping(bookings)) {
            throw new OverlappingBookingException();
        }

        internalList.setAll(bookings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NonOverlappingBookingList // instanceof handles nulls
                        && internalList.equals(((NonOverlappingBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code bookings} contains only unique bookings.
     */
    private boolean bookingsAreNonOverlapping(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).isOverlapping(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Replaces the old venue name {@code oldVenueName} in the booking with {@code newVenueName}.
     */
    public void updateVenueInBookings(VenueName oldVenueName, VenueName newVenueName) {
        for (int i = 0; i < internalList.size(); i++) {
            if (!(internalList.get(i).getVenueName().venueName.equalsIgnoreCase(oldVenueName.venueName))) {
                continue;
            }
            internalList.get(i).setVenueName(newVenueName);
        }
    }


    /**
     * Replaces the old person email {@code oldEmail} in the booking with {@code newEmail}.
     */
    public void updatePersonInBookings(Email oldEmail, Email newEmail) {
        for (int i = 0; i < internalList.size(); i++) {
            if (!(internalList.get(i).getBookerEmail().value.equalsIgnoreCase(oldEmail.value))) {
                continue;
            }
            internalList.get(i).setEmail(newEmail);
        }
    }


    /**
     * Returns the number of overlapped booking with {@code toAdd} in the booking.
     */
    public long countOverlaps(Booking toAdd) {
        requireNonNull(toAdd);
        return internalList.stream().filter(toAdd::isOverlapping).count();
    }

    public void removeBookingWithVenue(VenueName venueName) {
        for (int i = 0; i < internalList.size(); i++) {
            if (!(internalList.get(i).getVenueName().venueName.equalsIgnoreCase(venueName.venueName))) {
                continue;
            }
            internalList.remove(i);
        }
    }

    public void removeBookingWithBooker(Email email) {
        for (int i = 0; i < internalList.size(); i++) {
            if (!(internalList.get(i).getBookerEmail().value.equalsIgnoreCase(email.value))) {
                continue;
            }
            internalList.remove(i);
        }
    }
}
