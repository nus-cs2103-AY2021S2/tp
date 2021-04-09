package seedu.address.model.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TypicalSchedules {
    public static final LocalDateTime START_DATE_ONE =
            LocalDateTime.of(2021, 3, 3, 0, 00, 00);
    public static final LocalDateTime END_DATE_ONE =
            LocalDateTime.of(2021, 3, 3, 23, 59, 59);
    public static final Schedulable SHORT_SCHEDULE_ONE = new SimplePeriod("one", START_DATE_ONE, END_DATE_ONE);

}
