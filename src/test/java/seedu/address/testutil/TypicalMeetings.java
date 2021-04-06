package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Meeting;

public class TypicalMeetings {

    public static final Meeting MEETING_TODAY = new MeetingBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.of(0, 0))
            .build();

    public static final Meeting MEETING_NOW = new MeetingBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.now())
            .build();

    public static final Meeting MEETING_1 = new MeetingBuilder()
            .withDate(LocalDate.of(2021, 1, 30))
            .withTime(LocalTime.of(12, 0))
            .withDescription("We went to eat lunch together")
            .build();

    public static final Meeting MEETING_2 = new MeetingBuilder()
            .withDate(LocalDate.of(2021, 1, 24))
            .withTime(LocalTime.of(10, 0))
            .withDescription("We went for brunch")
            .build();

    public static final Meeting MEETING_3 = new MeetingBuilder()
            .withDate(LocalDate.of(2022, 12, 31))
            .withTime(LocalTime.of(23, 59))
            .withDescription("We went to see some fireworks")
            .build();

    public static final Meeting MEETING_4 = new MeetingBuilder()
            .withDate(LocalDate.of(2021, 4, 1))
            .withTime(LocalTime.of(10, 0))
            .withDescription("We broke up lmao")
            .build();

    public static final Meeting LEAP_DAY = new MeetingBuilder()
            .withDate(LocalDate.of(2024, 1, 15))
            .withTime(LocalTime.of(20, 0))
            .withDescription("This is a leap day")
            .build();
}
