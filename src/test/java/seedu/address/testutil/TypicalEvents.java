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
}
