package seedu.address.model.issue;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Issue}'s {@code RoomNumber}, {@code Description} or {@code Tag} matches any of the keywords
 * given.
 */

public class IssueContainsKeywordsPredicate implements Predicate<Issue> {

    private final List<String> keywords;

    public IssueContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests if any of the keywords are contained in either the room's number, description or any of its tags.
     *
     * @param issue Issue whose room number and tags need to be checked to see if they contain
     *                  any of the given keywords.
     * @return True if keyword is contained in the room's number or tags.
     */
    @Override
    public boolean test(Issue issue) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsIgnoreCase(issue.getRoomNumber().value, keyword)
                        || StringUtil.containsIgnoreCase(issue.getDescription().value, keyword)
                        || issue.getTags().stream().anyMatch(t -> StringUtil.containsIgnoreCase(t.tagName, keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IssueContainsKeywordsPredicate // instanceof handles nulls
                        && keywords.equals(((IssueContainsKeywordsPredicate) other).keywords)); // state check
    }

}
