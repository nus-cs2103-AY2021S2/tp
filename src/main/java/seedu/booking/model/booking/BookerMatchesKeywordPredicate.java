package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;
import seedu.booking.model.person.Email;

/**
 * Tests that a {@code Bookings}'s {@code booker email} matches the booker email given.
 */
public class BookerMatchesKeywordPredicate implements Predicate<Booking> {

    private final Email emailKeyword;

    public BookerMatchesKeywordPredicate(Email emailKeyword) {
        this.emailKeyword = emailKeyword;
    }

    @Override
    public boolean test(Booking booking) {
        return StringUtil.containsWordIgnoreCase(emailKeyword.toString(), String.valueOf(booking.getBookerEmail()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookerMatchesKeywordPredicate // instanceof handles nulls
                && emailKeyword.equals(((BookerMatchesKeywordPredicate) other).emailKeyword)); // state check
    }
}
