package seedu.budgetbaby.logic.parser;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Contains utility methods used for parsing between Date, YearMonth and string representation of YearMonth.
 */
public class YearMonthParser {
    /**
     * Parses {@code timestamp} into an {@code YearMonth} and returns it.
     */
    public static YearMonth getYearMonth(Date timestamp) {
        return YearMonth.from(timestamp.toInstant()
            .atZone(ZoneId.systemDefault())
            .toLocalDate());
    }

    /**
     * Parses {@code yearMonthStr} into an {@code YearMonth} and returns it.
     */
    public static YearMonth getYearMonth(String yearMonthStr) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("M-yyyy");
        return YearMonth.parse(yearMonthStr, f);
    }

}
