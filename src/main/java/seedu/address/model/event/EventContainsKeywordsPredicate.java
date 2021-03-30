package seedu.address.model.event;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class EventContainsKeywordsPredicate implements Predicate<Event> {
    private final List<String> keywords;

    public EventContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Event event) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(event.getName().toString(), keyword)
                        || StringUtil.containsWordIgnoreCase(event.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((EventContainsKeywordsPredicate) other).keywords)); // state check
    }

}
