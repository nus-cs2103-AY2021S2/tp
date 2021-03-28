package seedu.address.commons.util;

import java.time.LocalDateTime;

public class DateTimeUtil {
    public static boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth()
                && date1.getYear() == date2.getYear()
                && date1.getMonth() == date2.getMonth();
    }
}
