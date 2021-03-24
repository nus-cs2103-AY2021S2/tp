package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.person.Email;


/**
 * Tests that a {@code Bookings}'s {@code booker email} matches the booker email given
 */
public class BookingContainsBookerPredicate implements Predicate<Booking> {

    private final String bookerEmail;

    public BookingContainsBookerPredicate(String bookerEmail) {
        this.bookerEmail = bookerEmail;
    }

    public String getBookerEmail() {
        return this.bookerEmail;
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getBookerEmail().equals(new Email(this.bookerEmail));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingContainsBookerPredicate)
                && bookerEmail.equals(((BookingContainsBookerPredicate) other).bookerEmail));
    }
}
