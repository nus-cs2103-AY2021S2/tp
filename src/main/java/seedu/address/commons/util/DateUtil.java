package seedu.address.commons.util;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.fee.Month;
import seedu.address.model.fee.Year;

/**
 * A class for date time manipulation.
 */
public class DateUtil {
    /**
     * Gets the first day of the {@code Month} and {@code Year} in local date time format.
     *
     * @param month Month of the expected local date time.
     * @param year Year of the expected local date time.
     * @return LocalDateTime of the month and year combined.
     */
    public static LocalDateTime getFirstDayOfMonth(Month month, Year year) {
        requireAllNonNull(month, year);
        return LocalDateTime.of(year.getYear(), month.getMonth(), 1, 0, 0);
    }

    /**
     * Parse the string input in the form yyyy-... to get the year
     * @param dateValue string input in yyyy-... form
     * @return true if year is in yyyy form and precedes a -
     * @throws ParseException
     */
    public static int parseYear(String dateValue) throws ParseException {
        try {
            String[] dateValueSplit = dateValue.split("-", 2);
            int year = Integer.parseInt(dateValueSplit[0]);
            return year;
        } catch (NumberFormatException | NullPointerException e) {
            throw new ParseException("invalid date input");
        }
    }

    /**
     * Checks if given {@code year} is valid, not giving the 2038 problem
     * @param year integer
     * @return true if {@code year} is between 1970 and 2037 inclusive.
     */
    public static boolean isValidYear(int year) {
        return year >= 1970 && year <= 2037;
    }
}
