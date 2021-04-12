package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Bookings}'s {@code tags} contains the tag given
 */
public class BookingTagContainsKeywordsPredicate implements Predicate<Booking> {
    private final Tag tag;

    public BookingTagContainsKeywordsPredicate(String tag) {
        this.tag = new Tag(tag);
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getTags().stream().anyMatch(tag -> tag.isSameTag(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingTagContainsKeywordsPredicate)
                && tag.equals(((BookingTagContainsKeywordsPredicate) other).tag));
    }
}
