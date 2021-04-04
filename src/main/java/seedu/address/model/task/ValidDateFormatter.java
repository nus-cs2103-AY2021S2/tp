package seedu.address.model.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * A ValidDateFormatter class that can be used to verify whether the input date is in the correct range
 */
public class ValidDateFormatter {

    /**
     * Check whether the valid input date is in the correct range
     *
     * @param date Input string for date
     * @return Boolean result of whether the date is within the correct range
     */
    public static boolean isValid(String date) {

        boolean isValidDate = false;

        try {
            // ResolverStyle.STRICT is used for checking of leap year, months with 30 and 31 days
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT)
            );

            isValidDate = true;
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            isValidDate = false;
        }

        return isValidDate;
    }
}
