package seedu.booking.model.booking;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Bookings}'s {@code VenueName} matches the venue name given
 */
public class BookingContainsVenuePredicate implements Predicate<Booking> {
    private final List<String> keywords;

    public BookingContainsVenuePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Booking booking) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(String.valueOf(booking.getVenueName()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingContainsVenuePredicate // instanceof handles nulls
                && keywords.equals(((BookingContainsVenuePredicate) other).keywords)); // state check
    }
}
