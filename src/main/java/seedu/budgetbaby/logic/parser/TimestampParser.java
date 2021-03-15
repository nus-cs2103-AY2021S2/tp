package seedu.budgetbaby.logic.parser;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * Contains utility methods used for parsing between Date and YearMonth.
 */
public class TimestampParser {
    /**
     * Parses {@code timestamp} into an {@code YearMonth} and returns it.
     */
    public static YearMonth getYearMonth(Date timestamp) {
        return YearMonth.from(timestamp.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
    }
}
