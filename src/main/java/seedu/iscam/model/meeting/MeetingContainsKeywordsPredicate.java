package seedu.iscam.model.meeting;

import static seedu.iscam.model.meeting.CompletionStatus.ARGUMENT_COMPLETE;
import static seedu.iscam.model.meeting.CompletionStatus.ARGUMENT_INCOMPLETE;

import java.util.List;
import java.util.function.Predicate;

import seedu.iscam.commons.util.StringUtil;


/**
 * Tests that the details of a {@code Meeting} matches any of the keywords given.
 */
public class MeetingContainsKeywordsPredicate implements Predicate<Meeting> {
    private final String searchString;
    private final List<String> keywords;

    /**
     * Creates a MeetingContainsKeywordsPredicate to check if given {@code keywords} match any {Meeting}.
     */
    public MeetingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;

        String str = "";
        for (String keyword : keywords) {
            str = str.concat(keyword + " ");
        }
        searchString = str.strip().toLowerCase();
    }

    @Override
    public boolean test(Meeting meeting) {
        if (keywords.isEmpty()) {
            return false;
        }

        if (searchString.equals(ARGUMENT_COMPLETE.toLowerCase())) {
            return meeting.getStatus().isComplete();
        } else if (searchString.equals(ARGUMENT_INCOMPLETE.toLowerCase())) {
            return !meeting.getStatus().isComplete();
        } else {
            return keywords.stream()
                    .allMatch(keyword -> StringUtil.containsIgnoreCase(meeting.getClientName().toString(), keyword)
                            || StringUtil.containsIgnoreCase(meeting.getDateTime().toString(), keyword)
                            || meeting.getDateTime().getDayOfWeek().equals(keyword.toLowerCase())
                            || meeting.getDateTime().getDayOfWeekFull().equals(keyword.toLowerCase())
                            || StringUtil.containsIgnoreCase(meeting.getLocation().toString(), keyword)
                            || StringUtil.containsIgnoreCase(meeting.getDescription().toString(), keyword)
                            || meeting.getTags().stream()
                            .anyMatch(tag -> StringUtil.containsIgnoreCase(tag.toString(), keyword)));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof MeetingContainsKeywordsPredicate
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords));
    }
}
