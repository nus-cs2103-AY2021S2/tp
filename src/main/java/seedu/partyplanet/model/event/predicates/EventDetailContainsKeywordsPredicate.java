package seedu.partyplanet.model.event.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.event.Event;

/**
 * Tests that a {@code Event}'s {@code Remark} matches any of the keywords given.
 */
public class EventDetailContainsKeywordsPredicate implements Predicate<Event> {
    private final String keywords;

    public EventDetailContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Event event) {
        return event.getDetails().value.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventDetailContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventDetailContainsKeywordsPredicate) other).keywords)); // state check
    }

}
