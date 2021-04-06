package seedu.booking.model.venue;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Venue}'s {@code tags} contains the tag given
 */
public class VenueContainsTagPredicate implements Predicate<Venue> {
    private final Tag tag;

    public VenueContainsTagPredicate(String tagName) {
        this.tag = new Tag(tagName);
    }

    public String getTagName() {
        return this.tag.getTagName();
    }

    @Override
    public boolean test(Venue venue) {
        return venue.getTags().contains(this.tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof VenueContainsTagPredicate)
                && tag.equals(((VenueContainsTagPredicate) other).tag));
    }
}
