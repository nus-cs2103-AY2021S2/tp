package seedu.booking.model.booking;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Bookings}'s {@code Tags} contains any of the tag keywords given.
 */
public class BookingTagContainsKeywordsPredicate implements Predicate<Booking> {
    private final Set<Tag> tagSet;

    public BookingTagContainsKeywordsPredicate(Set<Tag> tagSet) {
        this.tagSet = new HashSet<Tag>(tagSet);
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getTags().stream().anyMatch(tagSet::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingTagContainsKeywordsPredicate)
                && tagSet.equals(((BookingTagContainsKeywordsPredicate) other).tagSet));
    }
}
