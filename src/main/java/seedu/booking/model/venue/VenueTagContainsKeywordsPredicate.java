package seedu.booking.model.venue;

import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Venue}'s {@code Tags} contains the tag given.
 */
public class VenueTagContainsKeywordsPredicate implements Predicate<Venue> {
    private final Tag tag;

    public VenueTagContainsKeywordsPredicate(String tagName) {
        this.tag = new Tag(tagName);
    }

    public String getTagName() {
        return this.tag.getTagName();
    }

    @Override
    public boolean test(Venue venue) {
        return venue.getTags().stream().anyMatch(tag -> tag.isSameTag(this.tag));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof VenueTagContainsKeywordsPredicate)
                && tag.equals(((VenueTagContainsKeywordsPredicate) other).tag));
    }

}
