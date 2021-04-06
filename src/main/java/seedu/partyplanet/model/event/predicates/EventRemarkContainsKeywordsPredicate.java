package seedu.partyplanet.model.event.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.event.Event;

/**
 * Tests that a {@code Event}'s {@code Remark} matches any of the keywords given.
 */
public class EventRemarkContainsKeywordsPredicate implements Predicate<Event> {
    private final String keywords;

    public EventRemarkContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Event event) {
        return event.getRemark().value.toLowerCase().contains(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventRemarkContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventRemarkContainsKeywordsPredicate) other).keywords)); // state check
    }

}
