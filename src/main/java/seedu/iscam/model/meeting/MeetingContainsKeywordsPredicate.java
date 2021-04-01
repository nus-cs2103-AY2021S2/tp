package seedu.iscam.model.meeting;

import java.util.List;
import java.util.function.Predicate;

import seedu.iscam.commons.util.StringUtil;

/**
 * Tests that the details of a {@code Meeting} matches any of the keywords given.
 */
public class MeetingContainsKeywordsPredicate implements Predicate<Meeting> {
    private final List<String> keywords;

    public MeetingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Meeting meeting) {
        return keywords.stream()
                .allMatch(keyword -> StringUtil.containsIgnoreCase(meeting.getClientName().toString(), keyword)
                    || StringUtil.containsIgnoreCase(meeting.getDateTime().toString(), keyword)
                    || StringUtil.containsIgnoreCase(meeting.getLocation().toString(), keyword)
                    || StringUtil.containsIgnoreCase(meeting.getDescription().toString(), keyword)
                    || meeting.getTags().stream().anyMatch(tag ->
                        StringUtil.containsIgnoreCase(tag.toString(), keyword)));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof MeetingContainsKeywordsPredicate
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords));
    }
}
