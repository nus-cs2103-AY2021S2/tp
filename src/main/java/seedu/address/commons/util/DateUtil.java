package seedu.address.commons.util;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.model.AbstractDate;

public class DateUtil {
    /**
     * Checks whether the given date is before or is today
     */
    public static boolean isBeforeToday(AbstractDate date) {
        return !date.value.isAfter(LocalDate.now().atTime(LocalTime.MAX));
    }
}
