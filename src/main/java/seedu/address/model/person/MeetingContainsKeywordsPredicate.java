package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.model.meeting.Meeting;

/**
 * Tests that a {@code Person}'s {@code Meeting} matches any of the keywords given.
 */
public class MeetingContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public MeetingContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    static boolean checkMeeting(List<Meeting> meeting, String keyword) {
        boolean containsKeyword = false;

        for (Meeting meet : meeting) {
            if (meet.place.toLowerCase().contains(keyword.toLowerCase())
                    || meet.date.toLowerCase().contains(keyword.toLowerCase())
                    || meet.time.toLowerCase().contains(keyword.toLowerCase())
                    || meet.meeting.toLowerCase().contains(keyword.toLowerCase())) {
                containsKeyword = true;
                break;
            }
        }
        return containsKeyword;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> checkMeeting(person.getMeeting(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords)); // state check
    }
}
