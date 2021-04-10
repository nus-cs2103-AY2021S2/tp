package chim.commons.util;

import java.time.LocalDate;
import java.time.LocalTime;

import chim.model.AbstractDate;

public class DateUtil {
    /**
     * Checks whether the given date is before or is today
     */
    public static boolean isBeforeToday(AbstractDate date) {
        return !date.value.isAfter(LocalDate.now().atTime(LocalTime.MAX));
    }
}
