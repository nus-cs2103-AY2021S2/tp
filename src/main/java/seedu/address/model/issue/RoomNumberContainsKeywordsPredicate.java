package seedu.address.model.issue;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Issue}'s {@code Name} matches any of the keywords given.
 */
public class RoomNumberContainsKeywordsPredicate implements Predicate<Issue> {

    private final List<String> keywords;

    public RoomNumberContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Issue issue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(issue.getRoomNumber().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RoomNumberContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((RoomNumberContainsKeywordsPredicate) other).keywords)); // state check
    }

}
