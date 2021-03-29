package seedu.address.commons.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;

/**
 * A class for date time manipulation.
 */
public class DateUtil {
    /**
     * Converts to the local date time format of the month and year combined.
     *
     * @param month Month of the expected local date time.
     * @param year Year of the expected local date time.
     * @return LocalDateTime of the month and year combined.
     */
    public static LocalDateTime convertToLocalDate(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }
}
