package seedu.address.model.medical;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateFormat {
    public static final DateTimeFormatter DATE_FORMAT_STORAGE =
            new DateTimeFormatterBuilder().appendPattern("d/M/yy H:mm")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    public static final DateTimeFormatter DATE_FORMAT_DISPLAY =
            new DateTimeFormatterBuilder().appendPattern("EEE, dd MMM, YYYY, HH:mm")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();
    public static final DateTimeFormatter DATE_FORMAT_NO_TIME =
            new DateTimeFormatterBuilder().appendPattern("dd MMM YYYY")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
}
