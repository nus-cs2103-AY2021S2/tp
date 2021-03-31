package seedu.booking.model.booking;

import java.util.function.Predicate;

/**
 * Tests that a {@code Bookings}'s {@code Venuename} matches the venuename given
 */
public class BookingContainsVenuePredicate implements Predicate<Booking> {
    private final String venueName;

    public BookingContainsVenuePredicate(String venueName) {
        this.venueName = venueName;
    }

    public String getVenueName() {
        return this.venueName;
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getVenueName().toString().equalsIgnoreCase(this.venueName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingContainsVenuePredicate)
                && venueName.equals(((BookingContainsVenuePredicate) other).venueName));
    }
}
