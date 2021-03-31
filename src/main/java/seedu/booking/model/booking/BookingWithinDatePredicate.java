package seedu.booking.model.booking;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Bookings} is on the date given
 */
public class BookingWithinDatePredicate implements Predicate<Booking> {

    private final LocalDate date;

    public BookingWithinDatePredicate(LocalDate date) {
        this.date = date;
    }

    public String getDateString() {
        return date.toString();
    }

    @Override
    public boolean test(Booking booking) {
        LocalDate startDate = booking.getBookingStart().getStartTime().toLocalDate();
        LocalDate endDate = booking.getBookingEnd().getEndTime().toLocalDate();

        return startDate.isEqual(date) || endDate.isEqual(date) || (startDate.isBefore(date) && endDate.isAfter(date));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingWithinDatePredicate)
                && date.equals(((BookingWithinDatePredicate) other).date));
    }

}
