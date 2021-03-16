package seedu.address.model.meeting;


import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Meeting}'s {@code MeetingName} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(meeting.getName().fullName, keyword));
    }


    @Override
    //TODO: Do we have to include full path name to disntinguish from other NamePredicate? Feels redundant.
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles
                // nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords));
        // state check
    }

}
