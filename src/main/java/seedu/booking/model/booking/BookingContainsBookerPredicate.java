package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.person.Name;

/**
 * Tests that a {@code Bookings}'s {@code booker name} matches the booker name given
 */
public class BookingContainsBookerPredicate implements Predicate<Booking> {

    private final String bookerName;

    public BookingContainsBookerPredicate(String bookerName) {
        this.bookerName = bookerName;
    }

    public String getBookerName() {
        return this.bookerName;
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getBooker().getName().equals(new Name(this.bookerName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingContainsBookerPredicate)
                && bookerName.equals(((BookingContainsBookerPredicate) other).bookerName));
    }
}