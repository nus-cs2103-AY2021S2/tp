package seedu.address.commons.util;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeUtil {
    public static final DateTimeFormatter DATETIME_FORMAT = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd HH:mm]")
            .appendPattern("[yyyy-MM-dd]")
            .appendPattern("[d-M-yyyy HH:mm]")
            .appendPattern("[d-M-yyyy]")
            .appendPattern("[yyyy/MM/dd HH:mm]")
            .appendPattern("[yyyy/MM/dd]")
            .appendPattern("[d/M/yyyy HH:mm]")
            .appendPattern("[d/M/yyyy]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
}
