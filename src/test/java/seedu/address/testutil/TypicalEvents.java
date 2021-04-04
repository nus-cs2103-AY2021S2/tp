package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.person.Event;

public class TypicalEvents {
    public static final Event MEETING_ONE = new EventBuilder()
            .withDate(LocalDate.of(2020, 12, 12))
            .withTime(LocalTime.of(12, 0))
            .withDescription("We went to eat lunch together")
            .build();

    public static final Event DATE_ONE = new EventBuilder()
            .withDate(LocalDate.of(2019, 10, 10))
            .withDescription("Anniversary")
            .build();

    public static final Event MEETING_TODAY = new EventBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.of(0, 0))
            .build();

    public static final Event MEETING_NOW = new EventBuilder()
            .withDate(LocalDate.now())
            .withTime(LocalTime.now())
            .build();
}
