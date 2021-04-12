package seedu.partyplanet.model.event.predicates;

import java.util.function.Predicate;

import seedu.partyplanet.model.event.Event;

/**
 * Tests that a {@code Event}'s {@code Remark} matches all of the keywords given.
 * Match is case-insensitive to account for fact that two names stored in alternate
 * case are semantically the same to the user.
 */
public class EventRemarkContainsExactKeywordsPredicate implements Predicate<Event> {
    private final String keywords;

    public EventRemarkContainsExactKeywordsPredicate(String keywords) {
        this.keywords = keywords.toLowerCase();
    }

    @Override
    public boolean test(Event event) {
        return event.getRemark().value.toLowerCase().equals(this.keywords);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventRemarkContainsExactKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventRemarkContainsExactKeywordsPredicate) other).keywords)); // state check
    }

}
