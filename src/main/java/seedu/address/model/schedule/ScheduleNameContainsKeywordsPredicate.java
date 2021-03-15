package seedu.address.model.schedule;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Schedule}'s {@code ScheduleDescription} matches any of the keywords given.
 */
public class ScheduleNameContainsKeywordsPredicate implements Predicate<Schedule> {

    private final List<String> keywords;

    public ScheduleNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Schedule schedule) {
        String scheduleDescription = schedule.getScheduleDescription().description;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(scheduleDescription, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleNameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ScheduleNameContainsKeywordsPredicate) other).keywords)); // state check
    }
}
