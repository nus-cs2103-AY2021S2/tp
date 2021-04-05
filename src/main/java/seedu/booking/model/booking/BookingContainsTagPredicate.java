package seedu.booking.model.booking;

import java.util.function.Predicate;

import seedu.booking.model.Tag;
import seedu.booking.model.person.Person;
import seedu.booking.model.person.PersonTagContainsKeywordsPredicate;

/**
 * Tests that a {@code Bookings}'s {@code tags} contains the tag given
 */
public class BookingContainsTagPredicate implements Predicate<Booking> {
    private final Tag tag;

    public BookingContainsTagPredicate(String tag) {
        this.tag = new Tag(tag);
    }

    public String getTagName() {
        return tag.getTagName();
    }

    @Override
    public boolean test(Booking booking) {
        return booking.getTags().stream().anyMatch(tag -> tag.isSameTag(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof BookingContainsTagPredicate)
                && tag.equals(((BookingContainsTagPredicate) other).tag));
    }
}
