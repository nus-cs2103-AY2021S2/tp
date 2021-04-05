package seedu.booking.model.venue;

import java.util.List;
import java.util.function.Predicate;

import seedu.booking.commons.util.StringUtil;

/**
 * Tests that a {@code Venue}'s {@code Description} matches any of the keywords given.
 */
public class VenueDescContainsKeywordsPredicate implements Predicate<Venue> {

    public static final String MESSAGE_CONSTRAINTS = "Keyword(s) for description cannot be empty";

    private final List<String> keywords;

    public VenueDescContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Venue venue) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsWordIgnoreCase(venue.getDescription(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VenueDescContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((VenueDescContainsKeywordsPredicate) other).keywords)); // state check
    }

}

