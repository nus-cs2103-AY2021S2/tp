package seedu.address.model.person;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    static boolean checkMeeting(List<Meeting> meetings, String keyword) {
        boolean containsKeyword = false;
        for (Meeting meeting : meetings) {
            if (meeting.meeting.toLowerCase().contains(keyword.toLowerCase())) {
                containsKeyword = true;
                break;
            }

            try {
                LocalTime keyTime = LocalTime.parse(keyword, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime meetingStart = LocalTime.parse(meeting.start, DateTimeFormatter.ofPattern("HH:mm"));
                LocalTime meetingEnd = LocalTime.parse(meeting.end, DateTimeFormatter.ofPattern("HH:mm"));
                if ((keyTime.isAfter(meetingStart) && keyTime.isBefore(meetingEnd))) {
                    containsKeyword = true;
                    break;
                }
            } catch (DateTimeParseException ex) {
                continue;
            }
        }
        return containsKeyword;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> checkMeeting(person.getMeetings(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MeetingContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((MeetingContainsKeywordsPredicate) other).keywords)); // state check
    }
}
