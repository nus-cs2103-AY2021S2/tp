package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Event;
import seedu.address.model.person.Meeting;

public class TypicalMeetings {
    public static final Meeting MEETING_ONE = new MeetingBuilder()
            .withDate(LocalDate.of(2020, 12, 12))
            .withTime(LocalTime.of(12, 0))
            .withDescription("We went to eat lunch together")
            .build();

    public static final Meeting MEETING_TODAY = new MeetingBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.of(0, 0))
            .build();

    public static final Meeting MEETING_NOW = new MeetingBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.now())
            .build();

    public static Meeting[] getTypicalMeetings() {
        return new Meeting[] {
                new Meeting(LocalDate.of(2021, 1, 30), LocalTime.of(10, 0), "m1"),
                new Meeting(LocalDate.of(2021, 1, 24), LocalTime.of(10, 0), "m2"),
                new Meeting(LocalDate.of(2022, 12, 30), LocalTime.of(10, 0), "m3"),
                new Meeting(LocalDate.of(2021, 4, 1), LocalTime.of(10, 0), "m4"),
        };
    }
}
