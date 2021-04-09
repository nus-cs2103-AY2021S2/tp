package seedu.address.model.residence;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
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
 *
 * Note: All operations that modify the contents of the {@code interalList} will return the sorted list
 * as described in {@code Booking}'s {@code compareTo} method.
 *
 * @see Booking#compareTo(Booking)
 */
public class BookingList implements Iterable<Booking> {
    //@@author Soorya
    private static final Logger logger = LogsCenter.getLogger(BookingList.class);

    //@@author Li Gang
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    //@@author Soorya
    public BookingList() {}

    public BookingList(List<Booking> residenceBookingList) {
        setBookings(residenceBookingList);
    }

    public BookingList(BookingList residenceBookingList) {
        resetBookings(residenceBookingList);
    }

    //@@author Li Gang
    /**
     * Returns true if the list contains a booking which overlaps with the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::doesOverlap);
    }

    /**
     * Checks if edited Booking overlaps with any other bookings in the current booking list.
     */
    public boolean containsExclude(Booking toExclude, Booking toCheck) {
        requireNonNull(toCheck);
        ObservableList<Booking> copyOfInternalList = FXCollections.observableArrayList();
        Iterator<Booking> iterator = internalList.iterator();
        while (iterator.hasNext()) {
            copyOfInternalList.add(iterator.next());
        }
        copyOfInternalList.remove(toExclude);
        return copyOfInternalList.stream().anyMatch(toCheck::doesOverlap);
    }

    /**
     * Adds a booking to the list.
     * The booking must not overlap with another booking which already exists in the list.
     * Returns a sorted booking list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new OverlappingBookingException();
        }
        internalList.add(toAdd);
        logger.fine("Sorting the booking list.");
        sortBookingList();
    }

    /**
     * Replaces the booking {@code target} in the list with {@code editedBooking}.
     * {@code target} must exist in the list.
     * The booking identity of {@code editedBooking} must not overlap with another existing booking in the list.
     * Returns a sorted booking list.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireAllNonNull(target, editedBooking);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BookingNotFoundException();
        }

        internalList.set(index, editedBooking);
        logger.fine("Sorting the booking list.");
        sortBookingList();
    }

    /**
     * Returns the size of booking list
     */
    public int getBookingListSize() {
        return internalList.size();
    }

    public Booking getBooking(int zeroBasedIndex) {
        return internalList.get(zeroBasedIndex);
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
     * Returns a sorted booking list.
     */
    public void resetBookings(BookingList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        logger.fine("Sorting the booking list.");
        sortBookingList();
    }

    /**
     * Replaces the contents of this list with {@code bookings}.
     * {@code bookings} must not contain overlapping bookings.
     * Returns a sorted booking list.
     */
    public void setBookings(List<Booking> bookings) {
        requireAllNonNull(bookings);
        if (!bookingsAreUnique(bookings)) {
            throw new OverlappingBookingException();
        }
        internalList.setAll(bookings);
        logger.fine("Sorting the booking list.");
        sortBookingList();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}
     * sorted as described in Booking's {@code compareTo} method.
     */
    //@@author Soorya
    public ObservableList<Booking> getValue() {
        return internalUnmodifiableList;
    }

    //@@author Li Gang
    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        if (getValue().isEmpty()) {
            builder.append("No bookings.");
        } else {
            builder.append("Bookings:\n");
            getValue().stream().forEach(obj -> builder.append(obj + "\n"));
        }
        return builder.toString();
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
    //@@author Wang Tao
    public void sortBookingList() {
        FXCollections.sort(internalList);
    }

}
