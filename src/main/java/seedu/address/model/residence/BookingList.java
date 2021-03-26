package seedu.address.model.residence;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.exceptions.BookingNotFoundException;
import seedu.address.model.booking.exceptions.OverlappingBookingException;

/**
 * A list of bookings that enforces no overlapping between its elements and does not allow nulls.
 * A booking is considered non-overlapping by comparing using {@code Booking#doesOverlap(Booking)}. As such, adding and
 * updating of bookings uses Booking#doesOverlap(Booking) so as to ensure that the booking being added or
 * updated does not overlap in timing with another booking in the UniqueBookingList. However, the removal of a booking
 * uses Booking#equals(Object) so as to ensure that the booking with exactly the same fields will be removed.
 * Supports a minimal set of list operations.
 *
 * @see Booking#doesOverlap(Booking)
 */
public class BookingList implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public BookingList() { }

    public BookingList(List<Booking> residenceBookingList) {
        setBookings(residenceBookingList);
    }

    public BookingList(BookingList residenceBookingList) {
        resetBookings(residenceBookingList);
    }

    /**
     * Returns true if the list contains a booking which overlaps with the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::doesOverlap);
    }

    /**
     * Adds a booking to the list.
     * The booking must not overlap with another booking which already exists in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new OverlappingBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list.
     * The booking identity of {@code editedBooking} must not overlap with another existing booking in the list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        if (contains(editedBooking)) {
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

    /**
     * Replaces all the {@code bookings} with those from the other {@code BookingList}.
     */
    public void resetBookings(BookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain overlapping bookings.
     * @return
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new OverlappingBookingException();
        }
        internalList.setAll(bookings);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> getValue() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        return getValue().size() + " bookings";
        // TODO: refine later
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingList // instanceof handles nulls
                        && internalList.equals(((BookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code bookings} contains only non-overlapping bookings.
     */
    private boolean bookingsAreUnique(List<Booking> bookings) {
        for (int i = 0; i < bookings.size() - 1; i++) {
            for (int j = i + 1; j < bookings.size(); j++) {
                if (bookings.get(i).doesOverlap(bookings.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
