package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Bookings}'s {@code tags} contains the tag given
 */
public class BookingContainsTagPredicate implements Predicate<Booking> {
    private final Tag tag;

    public BookingContainsTagPredicate(String tagName) {
        this.tag = new Tag(tagName);
    }

    public String getTagName() {
        return this.tag.getTagName();
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getTags().contains(this.tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingContainsTagPredicate)
                && tag.equals(((BookingContainsTagPredicate) other).tag));
    }
}
