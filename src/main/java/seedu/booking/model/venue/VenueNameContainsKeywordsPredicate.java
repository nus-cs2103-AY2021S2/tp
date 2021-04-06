package seedu.booking.model.venue;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Venue}'s {@code VenueName} matches any of the keywords given.
 */
public class VenueNameContainsKeywordsPredicate implements Predicate<Venue> {
    private final List<String> keywords;

    public VenueNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Venue venue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(String.valueOf(venue.getVenueName()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VenueNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
