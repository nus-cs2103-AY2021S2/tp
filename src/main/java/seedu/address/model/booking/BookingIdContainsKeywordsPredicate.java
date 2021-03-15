package seedu.address.model.booking;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Booking}'s {@code Id} matches the keyword given.
 */
public class BookingIdContainsKeywordsPredicate implements Predicate<Booking> {
    private final int keyword;

    public BookingIdContainsKeywordsPredicate(int keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Booking booking) {
        return keyword == booking.getId();
    }

}
