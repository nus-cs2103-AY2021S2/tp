package seedu.booking.model.booking;

import java.util.function.Predicate;

/**
 * Tests that a {@code Booking}'s {@code Id} matches the keyword given.
 */
public class BookingIdContainsKeywordsPredicate implements Predicate<Booking> {
    private final String keyword;

    public BookingIdContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Booking booking) {
        return keyword.equals(String.valueOf(booking.getId()));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookingIdContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((BookingIdContainsKeywordsPredicate) other).keyword)); // state check
    }

}
