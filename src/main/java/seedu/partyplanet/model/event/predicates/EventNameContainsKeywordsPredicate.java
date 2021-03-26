package seedu.partyplanet.model.event.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.event.Event;

/**
 * Tests that a {@code Event}'s {@code Name} matches any of the keywords given.
 */
public class EventNameContainsKeywordsPredicate implements Predicate<Event> {
    private final String keywords;

    public EventNameContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Event event) {
        return event.getName().fullName.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventNameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
