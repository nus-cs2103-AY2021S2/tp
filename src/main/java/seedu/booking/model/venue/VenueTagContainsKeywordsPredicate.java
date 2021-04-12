package seedu.booking.model.venue;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.booking.model.Tag;

/**
 * Tests that a {@code Venue}'s {@code Tags} contains any of the tag keywords given.
 */
public class VenueTagContainsKeywordsPredicate implements Predicate<Venue> {
    private final Set<Tag> tagSet;

    public VenueTagContainsKeywordsPredicate(Set<Tag> tagSet) {
        this.tagSet = new HashSet<Tag>(tagSet);
    }

    @Override
    public boolean test(Venue venue) {
        return venue.getTags().stream().anyMatch(tagSet::contains);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || ((other instanceof VenueTagContainsKeywordsPredicate)
                && tagSet.equals(((VenueTagContainsKeywordsPredicate) other).tagSet));
    }

}
