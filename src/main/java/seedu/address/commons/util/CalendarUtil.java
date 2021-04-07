package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

/**
 * Utility methods for calendar.
 */
public class CalendarUtil {
    /**
     * Converts string formatted time from calendar box to local date.
     *
     * @param formattedTime String format of time from calendar box.
     * @return
     */
    public static LocalDate calendarTextToDate(String formattedTime) {
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("MMM d yyyy").toFormatter(Locale.ENGLISH);
        return LocalDate.parse(formattedTime, df);
    }
}
